<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>参加活动记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hdRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${hdRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动Id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="hdid" name="hdid" ignore="ignore"
							   value="${hdRecordPage.hdid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户唯一标识:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="opendid" name="opendid" ignore="ignore"
							   value="${hdRecordPage.opendid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							总数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="total" name="total" ignore="ignore"
							   value="${hdRecordPage.total}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="addtime" name="addtime" ignore="ignore"
							     value="<fmt:formatDate value='${hdRecordPage.addtime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							nickname:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="nickname" name="nickname" ignore="ignore"
							   value="${hdRecordPage.nickname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>