package com.trs.zhq.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigsExample {
	protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public ConfigsExample() {
		this.oredCriteria = new ArrayList();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return this.orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return this.distinct;
	}

	public List<Criteria> getOredCriteria() {
		return this.oredCriteria;
	}

	public void or(Criteria criteria) {
		this.oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		this.oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (this.oredCriteria.size() == 0) {
			this.oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		this.oredCriteria.clear();
		this.orderByClause = null;
		this.distinct = false;
	}

	protected static abstract class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			this.criteria = new ArrayList();
		}

		public boolean isValid() {
			return this.criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return this.criteria;
		}

		public List<Criterion> getCriteria() {
			return this.criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			this.criteria.add(new ConfigsExample.Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
                                    String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			this.criteria.add(new ConfigsExample.Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
                                    Object value2, String property) {
			if ((value1 == null) || (value2 == null)) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			this.criteria.add(new ConfigsExample.Criterion(condition, value1,
					value2));
		}

		public ConfigsExample.Criteria andIDIsNull() {
			addCriterion("ID is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDIsNotNull() {
			addCriterion("ID is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDEqualTo(String value) {
			addCriterion("ID =", value, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDNotEqualTo(String value) {
			addCriterion("ID <>", value, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDGreaterThan(String value) {
			addCriterion("ID >", value, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDGreaterThanOrEqualTo(String value) {
			addCriterion("ID >=", value, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDLessThan(String value) {
			addCriterion("ID <", value, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDLessThanOrEqualTo(String value) {
			addCriterion("ID <=", value, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDLike(String value) {
			addCriterion("ID like", value, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDNotLike(String value) {
			addCriterion("ID not like", value, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDIn(List<String> values) {
			addCriterion("ID in", values, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDNotIn(List<String> values) {
			addCriterion("ID not in", values, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDBetween(String value1, String value2) {
			addCriterion("ID between", value1, value2, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andIDNotBetween(String value1,
                                                       String value2) {
			addCriterion("ID not between", value1, value2, "ID");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYIsNull() {
			addCriterion("CKEY is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYIsNotNull() {
			addCriterion("CKEY is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYEqualTo(String value) {
			addCriterion("CKEY =", value, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYNotEqualTo(String value) {
			addCriterion("CKEY <>", value, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYGreaterThan(String value) {
			addCriterion("CKEY >", value, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYGreaterThanOrEqualTo(String value) {
			addCriterion("CKEY >=", value, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYLessThan(String value) {
			addCriterion("CKEY <", value, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYLessThanOrEqualTo(String value) {
			addCriterion("CKEY <=", value, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYLike(String value) {
			addCriterion("CKEY like", value, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYNotLike(String value) {
			addCriterion("CKEY not like", value, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYIn(List<String> values) {
			addCriterion("CKEY in", values, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYNotIn(List<String> values) {
			addCriterion("CKEY not in", values, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYBetween(String value1,
                                                      String value2) {
			addCriterion("CKEY between", value1, value2, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCKEYNotBetween(String value1,
                                                         String value2) {
			addCriterion("CKEY not between", value1, value2, "CKEY");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESIsNull() {
			addCriterion("CVALUES is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESIsNotNull() {
			addCriterion("CVALUES is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESEqualTo(String value) {
			addCriterion("CVALUES =", value, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESNotEqualTo(String value) {
			addCriterion("CVALUES <>", value, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESGreaterThan(String value) {
			addCriterion("CVALUES >", value, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESGreaterThanOrEqualTo(
				String value) {
			addCriterion("CVALUES >=", value, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESLessThan(String value) {
			addCriterion("CVALUES <", value, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESLessThanOrEqualTo(String value) {
			addCriterion("CVALUES <=", value, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESLike(String value) {
			addCriterion("CVALUES like", value, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESNotLike(String value) {
			addCriterion("CVALUES not like", value, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESIn(List<String> values) {
			addCriterion("CVALUES in", values, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESNotIn(List<String> values) {
			addCriterion("CVALUES not in", values, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESBetween(String value1,
                                                         String value2) {
			addCriterion("CVALUES between", value1, value2, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCVALUESNotBetween(String value1,
                                                            String value2) {
			addCriterion("CVALUES not between", value1, value2, "CVALUES");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMEIsNull() {
			addCriterion("CRTIME is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMEIsNotNull() {
			addCriterion("CRTIME is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMEEqualTo(Date value) {
			addCriterion("CRTIME =", value, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMENotEqualTo(Date value) {
			addCriterion("CRTIME <>", value, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMEGreaterThan(Date value) {
			addCriterion("CRTIME >", value, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMEGreaterThanOrEqualTo(Date value) {
			addCriterion("CRTIME >=", value, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMELessThan(Date value) {
			addCriterion("CRTIME <", value, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMELessThanOrEqualTo(Date value) {
			addCriterion("CRTIME <=", value, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMEIn(List<Date> values) {
			addCriterion("CRTIME in", values, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMENotIn(List<Date> values) {
			addCriterion("CRTIME not in", values, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMEBetween(Date value1, Date value2) {
			addCriterion("CRTIME between", value1, value2, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRTIMENotBetween(Date value1,
                                                           Date value2) {
			addCriterion("CRTIME not between", value1, value2, "CRTIME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERIsNull() {
			addCriterion("CRUSER is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERIsNotNull() {
			addCriterion("CRUSER is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSEREqualTo(String value) {
			addCriterion("CRUSER =", value, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERNotEqualTo(String value) {
			addCriterion("CRUSER <>", value, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERGreaterThan(String value) {
			addCriterion("CRUSER >", value, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERGreaterThanOrEqualTo(
				String value) {
			addCriterion("CRUSER >=", value, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERLessThan(String value) {
			addCriterion("CRUSER <", value, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERLessThanOrEqualTo(String value) {
			addCriterion("CRUSER <=", value, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERLike(String value) {
			addCriterion("CRUSER like", value, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERNotLike(String value) {
			addCriterion("CRUSER not like", value, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERIn(List<String> values) {
			addCriterion("CRUSER in", values, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERNotIn(List<String> values) {
			addCriterion("CRUSER not in", values, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERBetween(String value1,
                                                        String value2) {
			addCriterion("CRUSER between", value1, value2, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSERNotBetween(String value1,
                                                           String value2) {
			addCriterion("CRUSER not between", value1, value2, "CRUSER");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMEIsNull() {
			addCriterion("CRUSAERNAME is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMEIsNotNull() {
			addCriterion("CRUSAERNAME is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMEEqualTo(String value) {
			addCriterion("CRUSAERNAME =", value, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMENotEqualTo(String value) {
			addCriterion("CRUSAERNAME <>", value, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMEGreaterThan(String value) {
			addCriterion("CRUSAERNAME >", value, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMEGreaterThanOrEqualTo(
				String value) {
			addCriterion("CRUSAERNAME >=", value, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMELessThan(String value) {
			addCriterion("CRUSAERNAME <", value, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMELessThanOrEqualTo(
				String value) {
			addCriterion("CRUSAERNAME <=", value, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMELike(String value) {
			addCriterion("CRUSAERNAME like", value, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMENotLike(String value) {
			addCriterion("CRUSAERNAME not like", value, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMEIn(List<String> values) {
			addCriterion("CRUSAERNAME in", values, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMENotIn(List<String> values) {
			addCriterion("CRUSAERNAME not in", values, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMEBetween(String value1,
                                                             String value2) {
			addCriterion("CRUSAERNAME between", value1, value2, "CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andCRUSAERNAMENotBetween(String value1,
                                                                String value2) {
			addCriterion("CRUSAERNAME not between", value1, value2,
					"CRUSAERNAME");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONIsNull() {
			addCriterion("DESCRIBTION is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONIsNotNull() {
			addCriterion("DESCRIBTION is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONEqualTo(String value) {
			addCriterion("DESCRIBTION =", value, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONNotEqualTo(String value) {
			addCriterion("DESCRIBTION <>", value, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONGreaterThan(String value) {
			addCriterion("DESCRIBTION >", value, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONGreaterThanOrEqualTo(
				String value) {
			addCriterion("DESCRIBTION >=", value, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONLessThan(String value) {
			addCriterion("DESCRIBTION <", value, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONLessThanOrEqualTo(
				String value) {
			addCriterion("DESCRIBTION <=", value, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONLike(String value) {
			addCriterion("DESCRIBTION like", value, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONNotLike(String value) {
			addCriterion("DESCRIBTION not like", value, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONIn(List<String> values) {
			addCriterion("DESCRIBTION in", values, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONNotIn(List<String> values) {
			addCriterion("DESCRIBTION not in", values, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONBetween(String value1,
                                                             String value2) {
			addCriterion("DESCRIBTION between", value1, value2, "DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andDESCRIBTIONNotBetween(String value1,
                                                                String value2) {
			addCriterion("DESCRIBTION not between", value1, value2,
					"DESCRIBTION");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1IsNull() {
			addCriterion("PROP1 is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1IsNotNull() {
			addCriterion("PROP1 is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1EqualTo(String value) {
			addCriterion("PROP1 =", value, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1NotEqualTo(String value) {
			addCriterion("PROP1 <>", value, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1GreaterThan(String value) {
			addCriterion("PROP1 >", value, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1GreaterThanOrEqualTo(String value) {
			addCriterion("PROP1 >=", value, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1LessThan(String value) {
			addCriterion("PROP1 <", value, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1LessThanOrEqualTo(String value) {
			addCriterion("PROP1 <=", value, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1Like(String value) {
			addCriterion("PROP1 like", value, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1NotLike(String value) {
			addCriterion("PROP1 not like", value, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1In(List<String> values) {
			addCriterion("PROP1 in", values, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1NotIn(List<String> values) {
			addCriterion("PROP1 not in", values, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1Between(String value1,
                                                       String value2) {
			addCriterion("PROP1 between", value1, value2, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP1NotBetween(String value1,
                                                          String value2) {
			addCriterion("PROP1 not between", value1, value2, "PROP1");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2IsNull() {
			addCriterion("PROP2 is null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2IsNotNull() {
			addCriterion("PROP2 is not null");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2EqualTo(Integer value) {
			addCriterion("PROP2 =", value, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2NotEqualTo(Integer value) {
			addCriterion("PROP2 <>", value, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2GreaterThan(Integer value) {
			addCriterion("PROP2 >", value, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2GreaterThanOrEqualTo(
				Integer value) {
			addCriterion("PROP2 >=", value, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2LessThan(Integer value) {
			addCriterion("PROP2 <", value, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2LessThanOrEqualTo(Integer value) {
			addCriterion("PROP2 <=", value, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2In(List<Integer> values) {
			addCriterion("PROP2 in", values, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2NotIn(List<Integer> values) {
			addCriterion("PROP2 not in", values, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2Between(Integer value1,
                                                       Integer value2) {
			addCriterion("PROP2 between", value1, value2, "PROP2");
			return (ConfigsExample.Criteria) this;
		}

		public ConfigsExample.Criteria andPROP2NotBetween(Integer value1,
                                                          Integer value2) {
			addCriterion("PROP2 not between", value1, value2, "PROP2");
			return (ConfigsExample.Criteria) this;
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
			return this.condition;
		}

		public Object getValue() {
			return this.value;
		}

		public Object getSecondValue() {
			return this.secondValue;
		}

		public boolean isNoValue() {
			return this.noValue;
		}

		public boolean isSingleValue() {
			return this.singleValue;
		}

		public boolean isBetweenValue() {
			return this.betweenValue;
		}

		public boolean isListValue() {
			return this.listValue;
		}

		public String getTypeHandler() {
			return this.typeHandler;
		}

		protected Criterion(String condition) {
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if ((value instanceof List)) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
                            String typeHandler) {
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

	public static class Criteria extends ConfigsExample.GeneratedCriteria {
	}
}

/*
 * Location: C:\Users\UserHao\Desktop\通用框架\wms\WEB-INF\classes\
 * 
 * Qualified Name: com.wms.model.ConfigsExample
 * 
 * JD-Core Version: 0.7.0.1
 */