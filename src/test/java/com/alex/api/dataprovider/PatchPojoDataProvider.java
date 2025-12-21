package com.alex.api.dataprovider;

import org.testng.annotations.DataProvider;
import com.alex.api.models.PatchPostModel;

public class PatchPojoDataProvider {

    @DataProvider(name = "validPatchPosts")
    public Object[][] validPatchPostsDP() {

        return new Object[][] {

            { new PatchPostModel("Patched Title 1") },

            { new PatchPostModel(null, "Patched Body Content 2") },

            { new PatchPostModel("Patched Title 3", "Patched Body 3") }
        };
    }
}
