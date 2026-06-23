package com.hms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalManagementSystemApplicationTest {

    @Test
    public void testInstantiation() {
        HospitalManagementSystemApplication app = new HospitalManagementSystemApplication();
        assertNotNull(app);
    }

}