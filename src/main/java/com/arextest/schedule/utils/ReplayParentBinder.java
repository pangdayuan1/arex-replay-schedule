package com.arextest.schedule.utils;

import com.arextest.schedule.model.ReplayActionCaseItem;
import com.arextest.schedule.model.ReplayActionItem;
import com.arextest.schedule.model.ReplayPlan;
import com.arextest.schedule.model.deploy.ServiceInstance;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jmo
 * @since 2021/10/12
 */
public final class ReplayParentBinder {

    private static final String HTTP_PROTOCOL = "http";
    private ReplayParentBinder() {

    }

    public static void setupReplayActionParent(List<ReplayActionItem> replayActionItemList, ReplayPlan replayPlan) {
        if (CollectionUtils.isEmpty(replayActionItemList)) {
            return;
        }
        for (ReplayActionItem actionItem : replayActionItemList) {
            actionItem.setParent(replayPlan);
        }
    }

    public static void setupCaseItemParent(List<ReplayActionCaseItem> sourceItemList, ReplayActionItem parent) {
        if (CollectionUtils.isEmpty(sourceItemList)) {
            return;
        }
        parent.setTargetInstance(filterTargetInstance(parent.getTargetInstance()));
        for (ReplayActionCaseItem caseItem : sourceItemList) {
            caseItem.setCaseType(parent.getActionType());
            caseItem.setParent(parent);
        }
    }

    private static List<ServiceInstance> filterTargetInstance(List<ServiceInstance> instances) {
        if (CollectionUtils.isEmpty(instances)) {
            return Collections.emptyList();
        }

        List<ServiceInstance> filterInstance= new ArrayList<>();
        Map<String, List<ServiceInstance>> collect = instances.stream().collect(Collectors.groupingBy(ServiceInstance::getIp));
        collect.forEach((k, v) -> {
            if (v != null && v.size() > 1) {
                Optional<ServiceInstance> serviceInstance = v.stream().filter(i -> i != null && HTTP_PROTOCOL.equalsIgnoreCase(i.getProtocol())).findFirst();
                serviceInstance.ifPresent(filterInstance::add);
            }else if (v != null && v.size() == 1){
                if (v.get(0) != null) {
                    filterInstance.add(v.get(0));
                }
            }
        });
        return filterInstance;
    }
}