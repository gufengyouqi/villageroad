package com.villageroad.service.impl;

import com.villageroad.service.DroolsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DroolsServiceImpl implements DroolsService {
//    @Resource
//    private RuleDao ruleDao;
//
//    @Resource
//    private KieServices kieServices;
//
//    @Resource
//    private EventService eventService;
//
//    @Resource
//    private List<RuleInfoConvert> ruleInfoConverts;
//
//    private final Map<String, KieContainer> kieContainerMap = new ConcurrentHashMap<>();
//
//    @Override
//    public Boolean reloadAllRule() {
//        try {
//            List<Rule> all = ruleDao.list();
//            Map<String, List<Rule>> taskTypeMap = all.stream().collect(Collectors.groupingBy(item -> item.getAppName() + "_" + item.getTaskType()));
//            taskTypeMap.forEach(this::reloadTaskTypeDroolsRule);
//            return true;
//        } catch (Exception e) {
//            log.error("reloadAllRule fail.", e);
//            throw new ToolchainBusinessException(DROOLS_INIT_FAIL);
//        }
//    }
//
//
////    @Async("buildExecutor")
//    @Override
//    public Boolean reloadTaskTypeRule(String appName, String taskType) {
//        try {
//            List<Rule> rules = ruleDao.findByTaskType(appName, taskType);
//            reloadTaskTypeDroolsRule(appName + "_" + taskType, rules);
//            return true;
//        } catch (Exception e) {
//            log.error("reloadTaskTypeRule fail taskType:{}", taskType, e);
//            throw new ToolchainBusinessException(DROOLS_INIT_FAIL);
//        }
//    }
//
//    @Override
//    public void triggerFact(EventInfo eventInfo) {
//        String key = eventInfo.getAppName() + "_" + eventInfo.getTaskType();
//        KieContainer kieContainer = kieContainerMap.get(key);
//        if (null == kieContainer) {
//            log.warn("Invalid type:{}, eventInfo:{}", eventInfo.getTaskType(), eventInfo);
//            return;
//        }
//        KieSession kieSession = kieContainer.newKieSession();
//        try {
//            kieSession.setGlobal("eventService", eventService);
//            Map<String, Object> parse = JSON.parseObject(eventInfo.getData());
//            parse.put("eventName", eventInfo.getEventName());
//            parse.put("appName", eventInfo.getAppName());
//            parse.put("taskType", eventInfo.getTaskType());
//            parse.put("taskId", eventInfo.getTaskId());
//            parse.put("id", eventInfo.getId());
//            kieSession.insert(parse);
//
//            kieSession.fireAllRules();
//        } finally {
//            kieSession.dispose();
//        }
//    }
//
//    private synchronized void reloadTaskTypeDroolsRule(String appNameTaskType, List<Rule> rules) {
//        String rulePath = "src/main/resources/rules/" + appNameTaskType + ".drl";
//        StringBuilder sb = new StringBuilder("package rules;\n");
//        sb.append("import java.util.Map;\n");
//        sb.append("import com.momenta.toolchain.monitor.service.EventService;\n");
//        sb.append("global EventService eventService;\n");
//        List<String> sbList = new ArrayList<>();
//
//        for (Rule rule : rules) {
//            if (StringUtils.isNotBlank(rule.getDroolsText())) {
//                sbList.add(rule.getDroolsText());
//            }
//        }
//        log.info("rule size:{}", sbList.size());
//        if (CollectionUtils.isEmpty(sbList)) {
//            log.warn("can not find rule");
//            kieContainerMap.remove(appNameTaskType);
//            return;
//        }
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        String rulesText = String.join("\n", sbList);
//        sb.append(rulesText);
//
//        String ruleContent = sb.toString();
//        kieFileSystem.write(rulePath, ruleContent);
//        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem).buildAll();
//        Results results = kb.getResults();
//        if (results.hasMessages(Message.Level.ERROR)) {
//            log.warn("reload rule fail, msg:{}", results.getMessages());
//            throw new ToolchainBusinessException(DROOLS_INIT_FAIL);
//        }
//
//        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
//        kieContainerMap.put(appNameTaskType, kieContainer);
//    }
//
//    @Override
//    public void simpleRuleCheck(Rule rule) {
//        String key = rule.getAppName() + "_" + rule.getTaskType();
//        String rulePath = "src/main/resources/rules/" + key + ".drl";
//        StringBuilder sb = new StringBuilder("package rules;\n");
//        sb.append("import java.util.Map;\n");
//        sb.append("import com.momenta.toolchain.monitor.service.EventService;\n");
//        sb.append("global EventService eventService;\n");
//
//        for (RuleInfoConvert ruleInfoConvert : ruleInfoConverts) {
//            if (ruleInfoConvert.checkMatch(rule)) {
//                sb.append(ruleInfoConvert.convertDroolsText(rule));
//                break;
//            }
//        }
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        String ruleContent = sb.toString();
//        kieFileSystem.write(rulePath, ruleContent);
//        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem).buildAll();
//        Results results = kb.getResults();
//        if (results.hasMessages(Message.Level.ERROR)) {
//            log.warn("reload rule fail, msg:{}", results.getMessages());
//            throw new ToolchainBusinessException(StatusCode.PARAM_NOT_VALID, "rule config param valid");
//        }
//
//    }
}
