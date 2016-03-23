<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="huodongController.do?save">
			<input id="id" name="id" type="hidden" value="${huodongPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" ignore="ignore"
							   value="${huodongPage.title}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动类型:
						</label>
					</td>
					<td class="value">
					<select id="type" name="type" ignore="ignore">
						<option value="1" <c:if test="${huodongPage.type==1}">selected="selected"</c:if>>刮刮乐</option>
						<option value="2" <c:if test="${huodongPage.type==2}">selected="selected"</c:if>>大转盘</option>
					</select>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="description" name="description" ignore="ignore"
							   value="${huodongPage.description}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							一等奖奖品:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="priceone" name="priceone" ignore="ignore"
							   value="${huodongPage.priceone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							一等奖数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="onetotal" name="onetotal" ignore="ignore"
							   value="${huodongPage.onetotal}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							二等奖奖品:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="pricetwo" name="pricetwo" ignore="ignore"
							   value="${huodongPage.pricetwo}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							二等奖数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="twototal" name="twototal" ignore="ignore"
							   value="${huodongPage.twototal}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							三等奖奖品:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="pricethree" name="pricethree" ignore="ignore"
							   value="${huodongPage.pricethree}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							三等奖数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="threetotal" name="threetotal" ignore="ignore"
							   value="${huodongPage.threetotal}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							抽奖次数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="count" name="count" ignore="ignore"
							   value="${huodongPage.count}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="starttime" name="starttime" ignore="ignore"
							     value="<fmt:formatDate value='${huodongPage.starttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结束时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="endtime" name="endtime" ignore="ignore"
							     value="<fmt:formatDate value='${huodongPage.endtime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							中奖概率:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="gl" name="gl" ignore="ignore"
							   value="${huodongPage.gl}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>