<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="cameraDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='cameraDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="cameraList.camera"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h2><fmt:message key="cameraDetail.heading"/></h2>
    <fmt:message key="cameraDetail.message"/>
</div>

<div class="col-sm-7">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="camera" method="post" action="cameraform" cssClass="well"
           id="cameraForm" onsubmit="return validateCamera(this)">
<form:hidden path="cameraId"/>
    <spring:bind path="camera.creationTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="camera.creationTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="creationTime" id="creationTime"  maxlength="255"/>
        <form:errors path="creationTime" cssClass="help-block"/>
    </div>
    <spring:bind path="camera.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="camera.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <!-- form:select cssClass="form-control" path="owner" items="ownerList" itemLabel="label" itemValue="value"/ -->

    <div class="form-group">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty camera.cameraId}">
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

<v:javascript formName="camera" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['cameraForm']).focus();
    });
</script>
