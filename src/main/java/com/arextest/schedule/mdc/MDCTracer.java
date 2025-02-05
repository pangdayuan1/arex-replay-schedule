package com.arextest.schedule.mdc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * @author jmo
 * @since 2021/11/5
 */
public final class MDCTracer {
    private static final String APP_ID = "appId";
    private static final String PLAN_ID = "planId";
    private static final String ACTION_ID = "actionId";
    private static final String DETAIL_ID = "detailId";

    private static final String PLAN_ITEM_ID = "planItemId";

    private static final String APP_TYPE = "app-type";
    private static final String AREX_SCHEDULE = "arex-schedule";

    private MDCTracer() {

    }

    private static void add(String name, long value) {
        MDC.put(name, String.valueOf(value));
    }

    public static void addAppType() {
        MDC.put(APP_TYPE, AREX_SCHEDULE);
    }

    public static void addAppId(String appId) {
        addAppType();
        if (StringUtils.isNotEmpty(appId)) {
            MDC.put(APP_ID, appId);
        }
    }

    public static void addPlanId(String planId) {
        addAppType();
        MDC.put(PLAN_ID, planId);
    }

    public static void addPlanItemId(String planItemId) {
        addAppType();
        MDC.put(PLAN_ITEM_ID, planItemId);
    }

    public static void addPlanId(long planId) {
        addAppType();
        add(PLAN_ID, planId);
    }

    public static void addActionId(String actionId) {
        addAppType();
        MDC.put(PLAN_ID, actionId);
    }

    public static void addActionId(long actionId) {
        addAppType();
        add(ACTION_ID, actionId);
    }

    public static void addDetailId(String detailId) {
        addAppType();
        MDC.put(DETAIL_ID, detailId);
    }

    public static void addDetailId(long detailId) {
        addAppType();
        add(DETAIL_ID, detailId);
    }

    public static void removeDetailId() {
        MDC.remove(DETAIL_ID);
    }

    public static void clear() {
        MDC.clear();
    }
}