package com.vakhnenko.departments.entity;

public class Constants {
    public static final long ROLE_GUEST = 0L;
    public static final long ROLE_USER = 1L;
    public static final long ROLE_ADMIN = 2L;

    public static final String ROLE_GUEST_NAME = "ROLE_GUEST";
    public static final String ROLE_USER_NAME = "ROLE_USER";
    public static final String ROLE_ADMIN_NAME = "ROLE_ADMIN";

    public static final String NAME_GUEST = "guest";
    public static final String NAME_USER = "user";
    public static final String NAME_ADMIN = "admin";

    public static final String PASSWORD_GUEST = "$2a$10$SIJNKIw3dWpuFMNGd/n6HO70tFoM.CoC044bNnvx5VNcLRHwn3DMK";
    public static final String PASSWORD_USER = "$2a$10$KiT/FGjwueFIhSTNnUvGOePa.JucZA5vvT8zRCVtLqZ0sL7sae/DK";
    public static final String PASSWORD_ADMIN = "$2a$10$SRikmt23vmocG0fn3lDLY.vxUdred2NMl5OpU1A89k6.2q2Oec8w.";

    public static final long MIN_LENGTH_USERNAME = 8;
    public static final long MAX_LENGTH_USERNAME = 32;
    public static final long MIN_LENGTH_PASSWORD = 8;
    public static final long MAX_LENGTH_PASSWORD = 32;

    private Constants() {
    }
}
