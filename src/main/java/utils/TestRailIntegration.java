package utils;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestRailIntegration {

    public enum StatusForTest{
        PASSED(1), BLOCKED(2), RETEST(4), FAILED(5);
        private int statusValue;

        public int getStatusValue() {
            return statusValue;
        }

        StatusForTest(int statusValue) {
            this.statusValue = statusValue;
        }
    }
    public static void main(String[] args) {

        //Получаем экзкмпляр TestRail
        String testRailUrl = "https://resterruster32wis.testrail.io/";
        String userName = "qaster7@qaster7.qas";
        String password = "FFff45t!!w";
        TestRail myTestRail = TestRail
                .builder(testRailUrl, userName, password).applicationName("TestRailIntegration").build();

        //Получить информацию о всех проектах
        List<Project> projectsList = myTestRail.projects().list().execute();
        for (Project project : projectsList) {
            System.out.println("Name of project is " + project.getName() + ". Id of project is " + project.getId());
        }

        //Получение всех айди проекта
        ArrayList<Integer> idOfProject = new ArrayList<>();
        for (Project project : projectsList) {
            idOfProject.add(project.getId());
        }
        System.out.println("====================");
        //вывод всех сьютов для проекта
        for (int i = 0; i < idOfProject.size(); i++) {
            List<Suite> suiteList = myTestRail.suites().list(idOfProject.get(i)).execute();
            for (Suite suite : suiteList) {
                System.out.println("Name of suite is " + suite.getName() + ". Id of suite is " + suite.getId());
            }
            System.out.println("================");
        }

        //вывод секций(либо папок) проекта
        List<Section> sectionList = myTestRail.sections().list(3).execute();
        for (Section section : sectionList) {
            System.out.println("Name of section is " + section.getName() + ". Id of section is " + section.getId());
        }
        System.out.println("=============CASES====================");
        //вывод тест-кейсов
        for (int i = 0; i < idOfProject.size()-1; i++) {
            List<CaseField> caseFieldList = myTestRail.caseFields().list().execute();
            List<Case> caseList = myTestRail.cases().list(idOfProject.get(i), caseFieldList).execute();
            for (Case testCase : caseList) {
                System.out.println("Name of testCase is " + testCase.getTitle() + ". Id of testCase is " + testCase.getId());
            }
            System.out.println("=======================");
        }

        //собрать все айди
        List<CaseField> caseFieldList = myTestRail.caseFields().list().execute();
        List<Case> caseList = myTestRail.cases().list(3, caseFieldList).execute();
        ArrayList<Integer> testCaseId = new ArrayList<>();
        for (Case testCase : caseList) {
            testCaseId.add(testCase.getId());
         }

        //создание тест-рана
        Run run = myTestRail.runs().add(3, new Run().setName("Lesson 12 Test Run 2")).execute();
        List<ResultField> resultFieldList = myTestRail.resultFields().list().execute();
        StatusForTest[] statusForTests = StatusForTest.values();
        int randomStatus = statusForTests[new Random().nextInt(statusForTests.length)].getStatusValue();
        //1- passed, 2 - blocked, 4 - retest, 5 - failed;
        for (Integer caseId: testCaseId){
         myTestRail.results().addForCase(run.getId(), caseId,
                 new Result().setStatusId
                         (statusForTests[new Random().nextInt(statusForTests.length)].getStatusValue()),
                 resultFieldList).execute();
        }
        //myTestRail.results().addForCase(run.getId(), 20, new Result().setStatusId(1), resultFieldList).execute();

        //закрываем тест ран
        //List<Run> runList = myTestRail.runs().list(3).execute();
        myTestRail.runs().close(run.getId()).execute();

    }
}
