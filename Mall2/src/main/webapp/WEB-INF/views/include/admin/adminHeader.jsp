<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %> 

<html>

<head>

	<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/back/admin.css" rel="stylesheet">

<script>
function checkEmpty(id, name){
	var value = $("#"+id).val();
	if(value.length==0){
		alert(name+ "不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	return true;
}
function checkNumber(id, name){
	var value = $("#"+id).val();
	if(value.length==0){
		alert(name+ "不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	if(isNaN(value)){
		alert(name+ "必须是数字");
		$("#"+id)[0].focus();
		return false;
	}
	
	return true;
}
function checkInt(id, name){
	var value = $("#"+id).val();
	if(value.length==0){
		alert(name+ "不能为空");
		$("#"+id)[0].focus();
		return false;
	}
	if(parseInt(value)!=value){
		alert(name+ "必须是整数");
		$("#"+id)[0].focus();
		return false;
	}
	
	return true;
}


$(function(){
	$("a").click(function(){
		//deleteLink是自定义的属性
		var deleteLink = $(this).attr("deleteLink");
		console.log(deleteLink);
		if("true"==deleteLink){
			var confirmDelete = confirm("确认要删除");
			if(confirmDelete)
				return true;
			return false;
		}
	});
})

</script>	
</head>

<body>

