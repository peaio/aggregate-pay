package vc.thinker.pay.model;

import com.sinco.mybatis.dal.core.AbstractExample;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import vc.thinker.pay.bo.PayConfigBO;

public class PayConfigExample extends AbstractExample<PayConfigBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public PayConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setLimit(int count) {
        this.limit = String.valueOf(count);
    }

    public void setLimit(int offset, int rows) {
        this.limit = new StringBuilder().append(String.valueOf(offset)).append(",").append(String.valueOf(rows)).toString();
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAlipayRateIsNull() {
            addCriterion("alipay_rate is null");
            return (Criteria) this;
        }

        public Criteria andAlipayRateIsNotNull() {
            addCriterion("alipay_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayRateEqualTo(BigDecimal value) {
            addCriterion("alipay_rate =", value, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateNotEqualTo(BigDecimal value) {
            addCriterion("alipay_rate <>", value, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateGreaterThan(BigDecimal value) {
            addCriterion("alipay_rate >", value, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("alipay_rate >=", value, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateLessThan(BigDecimal value) {
            addCriterion("alipay_rate <", value, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("alipay_rate <=", value, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateIn(List<BigDecimal> values) {
            addCriterion("alipay_rate in", values, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateNotIn(List<BigDecimal> values) {
            addCriterion("alipay_rate not in", values, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alipay_rate between", value1, value2, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAlipayRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alipay_rate not between", value1, value2, "alipayRate");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyIsNull() {
            addCriterion("app_private_key is null");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyIsNotNull() {
            addCriterion("app_private_key is not null");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyEqualTo(String value) {
            addCriterion("app_private_key =", value, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyNotEqualTo(String value) {
            addCriterion("app_private_key <>", value, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyGreaterThan(String value) {
            addCriterion("app_private_key >", value, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyGreaterThanOrEqualTo(String value) {
            addCriterion("app_private_key >=", value, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyLessThan(String value) {
            addCriterion("app_private_key <", value, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyLessThanOrEqualTo(String value) {
            addCriterion("app_private_key <=", value, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyLike(String value) {
            addCriterion("app_private_key like", value, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyNotLike(String value) {
            addCriterion("app_private_key not like", value, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyIn(List<String> values) {
            addCriterion("app_private_key in", values, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyNotIn(List<String> values) {
            addCriterion("app_private_key not in", values, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyBetween(String value1, String value2) {
            addCriterion("app_private_key between", value1, value2, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPrivateKeyNotBetween(String value1, String value2) {
            addCriterion("app_private_key not between", value1, value2, "appPrivateKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyIsNull() {
            addCriterion("app_public_key is null");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyIsNotNull() {
            addCriterion("app_public_key is not null");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyEqualTo(String value) {
            addCriterion("app_public_key =", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyNotEqualTo(String value) {
            addCriterion("app_public_key <>", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyGreaterThan(String value) {
            addCriterion("app_public_key >", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyGreaterThanOrEqualTo(String value) {
            addCriterion("app_public_key >=", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyLessThan(String value) {
            addCriterion("app_public_key <", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyLessThanOrEqualTo(String value) {
            addCriterion("app_public_key <=", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyLike(String value) {
            addCriterion("app_public_key like", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyNotLike(String value) {
            addCriterion("app_public_key not like", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyIn(List<String> values) {
            addCriterion("app_public_key in", values, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyNotIn(List<String> values) {
            addCriterion("app_public_key not in", values, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyBetween(String value1, String value2) {
            addCriterion("app_public_key between", value1, value2, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyNotBetween(String value1, String value2) {
            addCriterion("app_public_key not between", value1, value2, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeIsNull() {
            addCriterion("currency_code is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeIsNotNull() {
            addCriterion("currency_code is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeEqualTo(String value) {
            addCriterion("currency_code =", value, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeNotEqualTo(String value) {
            addCriterion("currency_code <>", value, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeGreaterThan(String value) {
            addCriterion("currency_code >", value, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("currency_code >=", value, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeLessThan(String value) {
            addCriterion("currency_code <", value, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeLessThanOrEqualTo(String value) {
            addCriterion("currency_code <=", value, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeLike(String value) {
            addCriterion("currency_code like", value, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeNotLike(String value) {
            addCriterion("currency_code not like", value, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeIn(List<String> values) {
            addCriterion("currency_code in", values, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeNotIn(List<String> values) {
            addCriterion("currency_code not in", values, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeBetween(String value1, String value2) {
            addCriterion("currency_code between", value1, value2, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andCurrencyCodeNotBetween(String value1, String value2) {
            addCriterion("currency_code not between", value1, value2, "currencyCode");
            return (Criteria) this;
        }

        public Criteria andInstallIsNull() {
            addCriterion("install is null");
            return (Criteria) this;
        }

        public Criteria andInstallIsNotNull() {
            addCriterion("install is not null");
            return (Criteria) this;
        }

        public Criteria andInstallEqualTo(Boolean value) {
            addCriterion("install =", value, "install");
            return (Criteria) this;
        }

        public Criteria andInstallNotEqualTo(Boolean value) {
            addCriterion("install <>", value, "install");
            return (Criteria) this;
        }

        public Criteria andInstallGreaterThan(Boolean value) {
            addCriterion("install >", value, "install");
            return (Criteria) this;
        }

        public Criteria andInstallGreaterThanOrEqualTo(Boolean value) {
            addCriterion("install >=", value, "install");
            return (Criteria) this;
        }

        public Criteria andInstallLessThan(Boolean value) {
            addCriterion("install <", value, "install");
            return (Criteria) this;
        }

        public Criteria andInstallLessThanOrEqualTo(Boolean value) {
            addCriterion("install <=", value, "install");
            return (Criteria) this;
        }

        public Criteria andInstallIn(List<Boolean> values) {
            addCriterion("install in", values, "install");
            return (Criteria) this;
        }

        public Criteria andInstallNotIn(List<Boolean> values) {
            addCriterion("install not in", values, "install");
            return (Criteria) this;
        }

        public Criteria andInstallBetween(Boolean value1, Boolean value2) {
            addCriterion("install between", value1, value2, "install");
            return (Criteria) this;
        }

        public Criteria andInstallNotBetween(Boolean value1, Boolean value2) {
            addCriterion("install not between", value1, value2, "install");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("mark like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("mark not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("mark not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPartnerIsNull() {
            addCriterion("partner is null");
            return (Criteria) this;
        }

        public Criteria andPartnerIsNotNull() {
            addCriterion("partner is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerEqualTo(String value) {
            addCriterion("partner =", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerNotEqualTo(String value) {
            addCriterion("partner <>", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerGreaterThan(String value) {
            addCriterion("partner >", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerGreaterThanOrEqualTo(String value) {
            addCriterion("partner >=", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerLessThan(String value) {
            addCriterion("partner <", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerLessThanOrEqualTo(String value) {
            addCriterion("partner <=", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerLike(String value) {
            addCriterion("partner like", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerNotLike(String value) {
            addCriterion("partner not like", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerIn(List<String> values) {
            addCriterion("partner in", values, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerNotIn(List<String> values) {
            addCriterion("partner not in", values, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerBetween(String value1, String value2) {
            addCriterion("partner between", value1, value2, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerNotBetween(String value1, String value2) {
            addCriterion("partner not between", value1, value2, "partner");
            return (Criteria) this;
        }

        public Criteria andPoundageIsNull() {
            addCriterion("poundage is null");
            return (Criteria) this;
        }

        public Criteria andPoundageIsNotNull() {
            addCriterion("poundage is not null");
            return (Criteria) this;
        }

        public Criteria andPoundageEqualTo(BigDecimal value) {
            addCriterion("poundage =", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotEqualTo(BigDecimal value) {
            addCriterion("poundage <>", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageGreaterThan(BigDecimal value) {
            addCriterion("poundage >", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("poundage >=", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageLessThan(BigDecimal value) {
            addCriterion("poundage <", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("poundage <=", value, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageIn(List<BigDecimal> values) {
            addCriterion("poundage in", values, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotIn(List<BigDecimal> values) {
            addCriterion("poundage not in", values, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("poundage between", value1, value2, "poundage");
            return (Criteria) this;
        }

        public Criteria andPoundageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("poundage not between", value1, value2, "poundage");
            return (Criteria) this;
        }

        public Criteria andSafeKeyIsNull() {
            addCriterion("safe_key is null");
            return (Criteria) this;
        }

        public Criteria andSafeKeyIsNotNull() {
            addCriterion("safe_key is not null");
            return (Criteria) this;
        }

        public Criteria andSafeKeyEqualTo(String value) {
            addCriterion("safe_key =", value, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyNotEqualTo(String value) {
            addCriterion("safe_key <>", value, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyGreaterThan(String value) {
            addCriterion("safe_key >", value, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyGreaterThanOrEqualTo(String value) {
            addCriterion("safe_key >=", value, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyLessThan(String value) {
            addCriterion("safe_key <", value, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyLessThanOrEqualTo(String value) {
            addCriterion("safe_key <=", value, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyLike(String value) {
            addCriterion("safe_key like", value, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyNotLike(String value) {
            addCriterion("safe_key not like", value, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyIn(List<String> values) {
            addCriterion("safe_key in", values, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyNotIn(List<String> values) {
            addCriterion("safe_key not in", values, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyBetween(String value1, String value2) {
            addCriterion("safe_key between", value1, value2, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSafeKeyNotBetween(String value1, String value2) {
            addCriterion("safe_key not between", value1, value2, "safeKey");
            return (Criteria) this;
        }

        public Criteria andSellerEmailIsNull() {
            addCriterion("seller_email is null");
            return (Criteria) this;
        }

        public Criteria andSellerEmailIsNotNull() {
            addCriterion("seller_email is not null");
            return (Criteria) this;
        }

        public Criteria andSellerEmailEqualTo(String value) {
            addCriterion("seller_email =", value, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailNotEqualTo(String value) {
            addCriterion("seller_email <>", value, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailGreaterThan(String value) {
            addCriterion("seller_email >", value, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("seller_email >=", value, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailLessThan(String value) {
            addCriterion("seller_email <", value, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailLessThanOrEqualTo(String value) {
            addCriterion("seller_email <=", value, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailLike(String value) {
            addCriterion("seller_email like", value, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailNotLike(String value) {
            addCriterion("seller_email not like", value, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailIn(List<String> values) {
            addCriterion("seller_email in", values, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailNotIn(List<String> values) {
            addCriterion("seller_email not in", values, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailBetween(String value1, String value2) {
            addCriterion("seller_email between", value1, value2, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andSellerEmailNotBetween(String value1, String value2) {
            addCriterion("seller_email not between", value1, value2, "sellerEmail");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyIsNull() {
            addCriterion("tenpay_key is null");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyIsNotNull() {
            addCriterion("tenpay_key is not null");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyEqualTo(String value) {
            addCriterion("tenpay_key =", value, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyNotEqualTo(String value) {
            addCriterion("tenpay_key <>", value, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyGreaterThan(String value) {
            addCriterion("tenpay_key >", value, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyGreaterThanOrEqualTo(String value) {
            addCriterion("tenpay_key >=", value, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyLessThan(String value) {
            addCriterion("tenpay_key <", value, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyLessThanOrEqualTo(String value) {
            addCriterion("tenpay_key <=", value, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyLike(String value) {
            addCriterion("tenpay_key like", value, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyNotLike(String value) {
            addCriterion("tenpay_key not like", value, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyIn(List<String> values) {
            addCriterion("tenpay_key in", values, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyNotIn(List<String> values) {
            addCriterion("tenpay_key not in", values, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyBetween(String value1, String value2) {
            addCriterion("tenpay_key between", value1, value2, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayKeyNotBetween(String value1, String value2) {
            addCriterion("tenpay_key not between", value1, value2, "tenpayKey");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerIsNull() {
            addCriterion("tenpay_partner is null");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerIsNotNull() {
            addCriterion("tenpay_partner is not null");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerEqualTo(String value) {
            addCriterion("tenpay_partner =", value, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerNotEqualTo(String value) {
            addCriterion("tenpay_partner <>", value, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerGreaterThan(String value) {
            addCriterion("tenpay_partner >", value, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerGreaterThanOrEqualTo(String value) {
            addCriterion("tenpay_partner >=", value, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerLessThan(String value) {
            addCriterion("tenpay_partner <", value, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerLessThanOrEqualTo(String value) {
            addCriterion("tenpay_partner <=", value, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerLike(String value) {
            addCriterion("tenpay_partner like", value, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerNotLike(String value) {
            addCriterion("tenpay_partner not like", value, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerIn(List<String> values) {
            addCriterion("tenpay_partner in", values, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerNotIn(List<String> values) {
            addCriterion("tenpay_partner not in", values, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerBetween(String value1, String value2) {
            addCriterion("tenpay_partner between", value1, value2, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTenpayPartnerNotBetween(String value1, String value2) {
            addCriterion("tenpay_partner not between", value1, value2, "tenpayPartner");
            return (Criteria) this;
        }

        public Criteria andTradeModeIsNull() {
            addCriterion("trade_mode is null");
            return (Criteria) this;
        }

        public Criteria andTradeModeIsNotNull() {
            addCriterion("trade_mode is not null");
            return (Criteria) this;
        }

        public Criteria andTradeModeEqualTo(Integer value) {
            addCriterion("trade_mode =", value, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeNotEqualTo(Integer value) {
            addCriterion("trade_mode <>", value, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeGreaterThan(Integer value) {
            addCriterion("trade_mode >", value, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_mode >=", value, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeLessThan(Integer value) {
            addCriterion("trade_mode <", value, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeLessThanOrEqualTo(Integer value) {
            addCriterion("trade_mode <=", value, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeIn(List<Integer> values) {
            addCriterion("trade_mode in", values, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeNotIn(List<Integer> values) {
            addCriterion("trade_mode not in", values, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeBetween(Integer value1, Integer value2) {
            addCriterion("trade_mode between", value1, value2, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andTradeModeNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_mode not between", value1, value2, "tradeMode");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretIsNull() {
            addCriterion("wx_appSecret is null");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretIsNotNull() {
            addCriterion("wx_appSecret is not null");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretEqualTo(String value) {
            addCriterion("wx_appSecret =", value, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretNotEqualTo(String value) {
            addCriterion("wx_appSecret <>", value, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretGreaterThan(String value) {
            addCriterion("wx_appSecret >", value, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretGreaterThanOrEqualTo(String value) {
            addCriterion("wx_appSecret >=", value, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretLessThan(String value) {
            addCriterion("wx_appSecret <", value, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretLessThanOrEqualTo(String value) {
            addCriterion("wx_appSecret <=", value, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretLike(String value) {
            addCriterion("wx_appSecret like", value, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretNotLike(String value) {
            addCriterion("wx_appSecret not like", value, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretIn(List<String> values) {
            addCriterion("wx_appSecret in", values, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretNotIn(List<String> values) {
            addCriterion("wx_appSecret not in", values, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretBetween(String value1, String value2) {
            addCriterion("wx_appSecret between", value1, value2, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppsecretNotBetween(String value1, String value2) {
            addCriterion("wx_appSecret not between", value1, value2, "wxAppsecret");
            return (Criteria) this;
        }

        public Criteria andWxAppidIsNull() {
            addCriterion("wx_appid is null");
            return (Criteria) this;
        }

        public Criteria andWxAppidIsNotNull() {
            addCriterion("wx_appid is not null");
            return (Criteria) this;
        }

        public Criteria andWxAppidEqualTo(String value) {
            addCriterion("wx_appid =", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotEqualTo(String value) {
            addCriterion("wx_appid <>", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidGreaterThan(String value) {
            addCriterion("wx_appid >", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidGreaterThanOrEqualTo(String value) {
            addCriterion("wx_appid >=", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLessThan(String value) {
            addCriterion("wx_appid <", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLessThanOrEqualTo(String value) {
            addCriterion("wx_appid <=", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLike(String value) {
            addCriterion("wx_appid like", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotLike(String value) {
            addCriterion("wx_appid not like", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidIn(List<String> values) {
            addCriterion("wx_appid in", values, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotIn(List<String> values) {
            addCriterion("wx_appid not in", values, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidBetween(String value1, String value2) {
            addCriterion("wx_appid between", value1, value2, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotBetween(String value1, String value2) {
            addCriterion("wx_appid not between", value1, value2, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyIsNull() {
            addCriterion("wx_paySignKey is null");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyIsNotNull() {
            addCriterion("wx_paySignKey is not null");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyEqualTo(String value) {
            addCriterion("wx_paySignKey =", value, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyNotEqualTo(String value) {
            addCriterion("wx_paySignKey <>", value, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyGreaterThan(String value) {
            addCriterion("wx_paySignKey >", value, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyGreaterThanOrEqualTo(String value) {
            addCriterion("wx_paySignKey >=", value, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyLessThan(String value) {
            addCriterion("wx_paySignKey <", value, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyLessThanOrEqualTo(String value) {
            addCriterion("wx_paySignKey <=", value, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyLike(String value) {
            addCriterion("wx_paySignKey like", value, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyNotLike(String value) {
            addCriterion("wx_paySignKey not like", value, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyIn(List<String> values) {
            addCriterion("wx_paySignKey in", values, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyNotIn(List<String> values) {
            addCriterion("wx_paySignKey not in", values, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyBetween(String value1, String value2) {
            addCriterion("wx_paySignKey between", value1, value2, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andWxPaysignkeyNotBetween(String value1, String value2) {
            addCriterion("wx_paySignKey not between", value1, value2, "wxPaysignkey");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNull() {
            addCriterion("pay_channel is null");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNotNull() {
            addCriterion("pay_channel is not null");
            return (Criteria) this;
        }

        public Criteria andPayChannelEqualTo(String value) {
            addCriterion("pay_channel =", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotEqualTo(String value) {
            addCriterion("pay_channel <>", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThan(String value) {
            addCriterion("pay_channel >", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThanOrEqualTo(String value) {
            addCriterion("pay_channel >=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThan(String value) {
            addCriterion("pay_channel <", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThanOrEqualTo(String value) {
            addCriterion("pay_channel <=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLike(String value) {
            addCriterion("pay_channel like", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotLike(String value) {
            addCriterion("pay_channel not like", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelIn(List<String> values) {
            addCriterion("pay_channel in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotIn(List<String> values) {
            addCriterion("pay_channel not in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelBetween(String value1, String value2) {
            addCriterion("pay_channel between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotBetween(String value1, String value2) {
            addCriterion("pay_channel not between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andSignTypeIsNull() {
            addCriterion("sign_type is null");
            return (Criteria) this;
        }

        public Criteria andSignTypeIsNotNull() {
            addCriterion("sign_type is not null");
            return (Criteria) this;
        }

        public Criteria andSignTypeEqualTo(String value) {
            addCriterion("sign_type =", value, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeNotEqualTo(String value) {
            addCriterion("sign_type <>", value, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeGreaterThan(String value) {
            addCriterion("sign_type >", value, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sign_type >=", value, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeLessThan(String value) {
            addCriterion("sign_type <", value, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeLessThanOrEqualTo(String value) {
            addCriterion("sign_type <=", value, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeLike(String value) {
            addCriterion("sign_type like", value, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeNotLike(String value) {
            addCriterion("sign_type not like", value, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeIn(List<String> values) {
            addCriterion("sign_type in", values, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeNotIn(List<String> values) {
            addCriterion("sign_type not in", values, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeBetween(String value1, String value2) {
            addCriterion("sign_type between", value1, value2, "signType");
            return (Criteria) this;
        }

        public Criteria andSignTypeNotBetween(String value1, String value2) {
            addCriterion("sign_type not between", value1, value2, "signType");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdIsNull() {
            addCriterion("alipay_app_id is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdIsNotNull() {
            addCriterion("alipay_app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdEqualTo(String value) {
            addCriterion("alipay_app_id =", value, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdNotEqualTo(String value) {
            addCriterion("alipay_app_id <>", value, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdGreaterThan(String value) {
            addCriterion("alipay_app_id >", value, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_app_id >=", value, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdLessThan(String value) {
            addCriterion("alipay_app_id <", value, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdLessThanOrEqualTo(String value) {
            addCriterion("alipay_app_id <=", value, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdLike(String value) {
            addCriterion("alipay_app_id like", value, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdNotLike(String value) {
            addCriterion("alipay_app_id not like", value, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdIn(List<String> values) {
            addCriterion("alipay_app_id in", values, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdNotIn(List<String> values) {
            addCriterion("alipay_app_id not in", values, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdBetween(String value1, String value2) {
            addCriterion("alipay_app_id between", value1, value2, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayAppIdNotBetween(String value1, String value2) {
            addCriterion("alipay_app_id not between", value1, value2, "alipayAppId");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyIsNull() {
            addCriterion("alipay_public_key is null");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyIsNotNull() {
            addCriterion("alipay_public_key is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyEqualTo(String value) {
            addCriterion("alipay_public_key =", value, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyNotEqualTo(String value) {
            addCriterion("alipay_public_key <>", value, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyGreaterThan(String value) {
            addCriterion("alipay_public_key >", value, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_public_key >=", value, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyLessThan(String value) {
            addCriterion("alipay_public_key <", value, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyLessThanOrEqualTo(String value) {
            addCriterion("alipay_public_key <=", value, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyLike(String value) {
            addCriterion("alipay_public_key like", value, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyNotLike(String value) {
            addCriterion("alipay_public_key not like", value, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyIn(List<String> values) {
            addCriterion("alipay_public_key in", values, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyNotIn(List<String> values) {
            addCriterion("alipay_public_key not in", values, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyBetween(String value1, String value2) {
            addCriterion("alipay_public_key between", value1, value2, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andAlipayPublicKeyNotBetween(String value1, String value2) {
            addCriterion("alipay_public_key not between", value1, value2, "alipayPublicKey");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathIsNull() {
            addCriterion("wx_cert_local_path is null");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathIsNotNull() {
            addCriterion("wx_cert_local_path is not null");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathEqualTo(String value) {
            addCriterion("wx_cert_local_path =", value, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathNotEqualTo(String value) {
            addCriterion("wx_cert_local_path <>", value, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathGreaterThan(String value) {
            addCriterion("wx_cert_local_path >", value, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathGreaterThanOrEqualTo(String value) {
            addCriterion("wx_cert_local_path >=", value, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathLessThan(String value) {
            addCriterion("wx_cert_local_path <", value, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathLessThanOrEqualTo(String value) {
            addCriterion("wx_cert_local_path <=", value, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathLike(String value) {
            addCriterion("wx_cert_local_path like", value, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathNotLike(String value) {
            addCriterion("wx_cert_local_path not like", value, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathIn(List<String> values) {
            addCriterion("wx_cert_local_path in", values, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathNotIn(List<String> values) {
            addCriterion("wx_cert_local_path not in", values, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathBetween(String value1, String value2) {
            addCriterion("wx_cert_local_path between", value1, value2, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertLocalPathNotBetween(String value1, String value2) {
            addCriterion("wx_cert_local_path not between", value1, value2, "wxCertLocalPath");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicIsNull() {
            addCriterion("wx_rsa_public is null");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicIsNotNull() {
            addCriterion("wx_rsa_public is not null");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicEqualTo(String value) {
            addCriterion("wx_rsa_public =", value, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicNotEqualTo(String value) {
            addCriterion("wx_rsa_public <>", value, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicGreaterThan(String value) {
            addCriterion("wx_rsa_public >", value, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicGreaterThanOrEqualTo(String value) {
            addCriterion("wx_rsa_public >=", value, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicLessThan(String value) {
            addCriterion("wx_rsa_public <", value, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicLessThanOrEqualTo(String value) {
            addCriterion("wx_rsa_public <=", value, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicLike(String value) {
            addCriterion("wx_rsa_public like", value, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicNotLike(String value) {
            addCriterion("wx_rsa_public not like", value, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicIn(List<String> values) {
            addCriterion("wx_rsa_public in", values, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicNotIn(List<String> values) {
            addCriterion("wx_rsa_public not in", values, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicBetween(String value1, String value2) {
            addCriterion("wx_rsa_public between", value1, value2, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andWxRsaPublicNotBetween(String value1, String value2) {
            addCriterion("wx_rsa_public not between", value1, value2, "wxRsaPublic");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyIsNull() {
            addCriterion("stripe_api_key is null");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyIsNotNull() {
            addCriterion("stripe_api_key is not null");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyEqualTo(String value) {
            addCriterion("stripe_api_key =", value, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyNotEqualTo(String value) {
            addCriterion("stripe_api_key <>", value, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyGreaterThan(String value) {
            addCriterion("stripe_api_key >", value, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyGreaterThanOrEqualTo(String value) {
            addCriterion("stripe_api_key >=", value, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyLessThan(String value) {
            addCriterion("stripe_api_key <", value, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyLessThanOrEqualTo(String value) {
            addCriterion("stripe_api_key <=", value, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyLike(String value) {
            addCriterion("stripe_api_key like", value, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyNotLike(String value) {
            addCriterion("stripe_api_key not like", value, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyIn(List<String> values) {
            addCriterion("stripe_api_key in", values, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyNotIn(List<String> values) {
            addCriterion("stripe_api_key not in", values, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyBetween(String value1, String value2) {
            addCriterion("stripe_api_key between", value1, value2, "stripeApiKey");
            return (Criteria) this;
        }

        public Criteria andStripeApiKeyNotBetween(String value1, String value2) {
            addCriterion("stripe_api_key not between", value1, value2, "stripeApiKey");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}