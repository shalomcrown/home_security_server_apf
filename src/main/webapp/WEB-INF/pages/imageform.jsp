<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="imageDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='imageDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="imageList.image"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="imageDetail.heading"/></h2>
    <fmt:message key="imageDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="image" method="post" action="imageform" cssClass="well"
           id="imageForm" onsubmit="return validateImage(this)">
<form:hidden path="id"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <!-- form:select cssClass="form-control" path="camera" items="cameraList" itemLabel="label" itemValue="value"/ -->
    <spring:bind path="image.filename">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="image.filename" styleClass="control-label"/>
        <form:input cssClass="form-control" path="filename" id="filename"  maxlength="255"/>
        <form:errors path="filename" cssClass="help-block"/>
    </div>
    <spring:bind path="image.uploadTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="image.uploadTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="uploadTime" id="uploadTime"  maxlength="255"/>
        <form:errors path="uploadTime" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty image.id}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="image" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['imageForm']).focus();
    });
</script>
