package com.jasenmoloy.recyclerviewstresstest.domain.models;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jasenmoloy on 8/15/16.
 */
public class TestData {

    public static final BaseTestDataModel[] DATA = new BaseTestDataModel[] {
            new BaseTestDataModel("Title 1", "Blah blah blah"),
            new BaseTestDataModel("Title 2", "Blah blah blah"),
            new BaseTestDataModel("Title 3", "Blah blah blah"),
            new BaseTestDataModel("Title 4", "Blah blah blah"),
            new BaseTestDataModel("Title 5", "Blah blah blah"),
            new BaseTestDataModel("Title 6", "Blah blah blah"),
            new BaseTestDataModel("Title 7", "Blah blah blah"),
            new BaseTestDataModel("Title 8", "Blah blah blah"),
            new BaseTestDataModel("Title 9", "Blah blah blah"),
            new BaseTestDataModel("Title 10", "Blah blah blah"),
            new BaseTestDataModel("Title 11", "Blah blah blah"),
            new BaseTestDataModel("Title 12", "Blah blah blah"),
            new BaseTestDataModel("Title 13", "Blah blah blah"),
            new BaseTestDataModel("Title 14", "Blah blah blah"),
            new BaseTestDataModel("Title 15", "Blah blah blah"),
            new BaseTestDataModel("Title 16", "Blah blah blah"),
            new BaseTestDataModel("Title 17", "Blah blah blah"),
            new BaseTestDataModel("Title 18", "Blah blah blah"),
            new BaseTestDataModel("Title 19", "Blah blah blah"),
            new BaseTestDataModel("Title 20", "Blah blah blah"),
    };

    public static List<BaseTestDataModel> buildTestData() {
        return Arrays.asList(DATA);
    }

}
