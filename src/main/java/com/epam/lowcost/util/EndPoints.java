package com.epam.lowcost.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class EndPoints {
//  CRUD endpoints

    public static final String ALL = "/all";
    public static final String ADD = "/add";
    public static final String SEARCH = "/search";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";


//  Flight Controller endpoints

    public static final String FLIGHTS = "/flights";
    public static final String NEW_TICKET = "/new-ticket";

//  Login Controller endpoints

    public static final String ENTRY = "/entry";
    public static final String ADMIN_PANEL = "/admin-panel";
    public static final String REGISTRATION = "/registration";
    public static final String LOG_OUT = "/log-out";

//  Plane Controller endpoints

    public static final String PLANE = "/plane";

//  Tickets Controller endpoints

    public static final String TICKETS = "/tickets";
    public static final String SELF = "/self";

//  User Controller endpoints

    public static final String USER = "/user";
    public static final String ENROLL = "/enroll";
    public static final String SETTINGS = "/settings";
    public static final String CHANGE_PASSWORD = "/change-password";


}

