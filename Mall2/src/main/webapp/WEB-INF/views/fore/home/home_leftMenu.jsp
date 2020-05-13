<%--
  Created by IntelliJ IDEA.
  User: Yang xu
  Date: 2019/12/12
  Time: 22:27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div class="cate_right" >
    <%--从数据库里面拿--%>
    <%--
    <c:forEach items="${list}" var="cg">
         <span class="block_span" fid="${cg.id}"><span class="glyphicon glyphicon-link"></span><a href="#">${cg.name}</a></span>
     </c:forEach>
     --%>
</div>
<%--委托事件div.原来是 ajax载入新dom之前js 就加载完了，事件当然没有绑定到新载入的dom上--%>
