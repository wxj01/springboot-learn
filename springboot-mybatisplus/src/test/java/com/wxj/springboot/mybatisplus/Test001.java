//package com.wxj.springboot.mybatisplus;
//
//public class Test001 {
//}
//
////抽象策略类，定义规则校验方法
//abstract class RuleStrategy {
//    abstract public function ruleCheck();
//}//具体算法类，实现具体的规则逻辑
//
//class RulePay extends RuleStrategy {
//    public function ruleCheck() {        //处理充值规则逻辑
//    }
//}
//
//class RuleLogin extends RuleStrategy {
//    public function ruleCheck() {        //处理登录规则逻辑
//    }
//}
//
//class RuleLevel extends RuleStrategy {
//    public function ruleCheck() {        //处理等级规则逻辑
//    }
//}//环境类，对策略实现的封装，在客户端中使用
//
//class RuleContext {
//    public $strategy;
//
//    public function __construct(RuleStrategy $rs) {
//        $this -> strategy = $rs;
//    }
//
//    public function doCheck() {
//        return $this -> strategy -> ruleCheck();
//    }
//}
//
//class Client {    //检查规则
//    public function rule_check($rule_type) {
//        switch ($rule_type) {
//            case self::RULE_TYPE_PAY:
//                $strategy = new RulePay();
//                break;
//            case self::RULE_TYPE_LOGIN:
//                $strategy = new RuleLogin();
//                break;
//            case self::RULE_TYPE_LEVEL:
//                $strategy = new RuleLevel();
//                break;
//            default:                //默认处理
//                break;
//        }        //获取具体的规则策略
//        $rule_context = new RuleContext($strategy);        //执行具体的规则逻辑
//        $rule_context -> doCheck();
//        return true;
//    }
//}
//
