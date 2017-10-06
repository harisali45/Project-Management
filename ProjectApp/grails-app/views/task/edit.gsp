<html>
<head>
    <meta name="layout" content="main" />
    <title>Projects</title>
</head>
<body>
<breadcrumb:list icon="edit" title="${task.id?'Edit Task':'New Task'}" ></breadcrumb:list>
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                <h2>
                    <g:if test="${task.id}">
                        ${task.title}
                        <small>Created on <g:formatDate date="${task.created}" format="EEE, d MMM yyyy" /> by ${task.reportedBy.name}
                        <taskStatus:showBadge class="top-right" status="${task.status}" />
                        </small>
                    </g:if>
                    <g:if test="${!task.id}">
                        <g:message code="new.message" args="['Task']" />
                        <small>Enter the details below and click on save</small>
                    </g:if>
                </h2>
            </div>
            <div class="body">
                <ul class="nav nav-tabs tab-nav-right" role="tablist">
                    <li role="presentation" class="active"><a href="#edit" data-toggle="tab" aria-expanded="true">
                        <i class="material-icons">edit</i>
                        Details</a></li>
                    <li role="presentation" class=""><a href="#comments" data-toggle="tab" aria-expanded="false">
                        <i class="material-icons">comment</i>
                        Comments</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade active in" id="edit">
                        <g:form class="form-horizontal" url="${g.createLink(controller: 'task', action: 'save')}">
                            <input type="hidden" name="id" value="${fieldValue(bean: task, field: 'id')}" />
                            <input type="hidden" name="project" value="${fieldValue(bean: task, field: 'project')}" />
                            <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label for="title">Title</label>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="text" id="title"  name="title" class="form-control" placeholder="Enter project title"
                                                   value="${fieldValue(bean: task, field: 'title')}" required />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label for="description">Description</label>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <g:textArea name="description" value="${fieldValue(bean: task, field: 'description')}"
                                                        class="form-control no-resize" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <g:if test="${task.assignedTo?.id == session.userId}">
                            <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label >Status</label>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <g:radio name="status" type="radio" id="ongoingStatus" value="ONGOING"
                                                   class="with-gap radio-col-orange" checked="${task.status == 'ONGOING'}" />
                                            <label for="ongoingStatus">Ongoing</label>
                                            <g:radio name="status" type="radio" id="completedStatus" checked="${task.status == 'COMPLETED'}"
                                                   class="with-gap radio-col-green" value="COMPLETED" />
                                            <label for="completedStatus">Completed</label>
                                            <g:radio name="status" type="radio" id="cancelledStatus" value="CANCELLED" class="with-gap radio-col-red" />
                                            <label for="cancelledStatus">Cancelled</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </g:if>

                            <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label for="deadline">Deadline</label>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="text" class="datepicker form-control" placeholder="Select deadline"
                                            id="deadline" name="deadline" value="${g.formatDate(format:"dd/MM/yyyy", date: task.deadline)}" ></input>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label >Assigned To</label>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <g:select id="assignedTo" name="assignedTo"
                                              from="${usersInProject}"
                                              value="${task.assignedTo?.id}"
                                              optionKey="id"
                                              optionValue="name"
                                              data-live-search="true"
                                              class="selectpicker"
                                              noSelection="['':'-------- Users --------']"
                                    />
                                </div>
                            </div>

                            <div class="row clearfix">
                                <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                                    <button type="submit" class="btn btn-primary m-t-15 waves-effect" >
                                        <i class="material-icons">edit</i>
                                        <span>
                                            Update
                                        </span>
                                    </button>
                                </div>
                            </div>
                        </g:form>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="comments">
                        <g:if test="${task.id}">
                        <g:each in="${task.comment}" var="comment">
                            <div class="col-lg-2 col-md-2 col-sm-12">
                                <i class="material-icons md-48 pull-right">account_circle</i>
                            </div>
                            <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12" >
                            %{--<div class="col-lg-offset-2 col-md-offset-2 col-lg-10 col-md-10 col-sm-12 col-xs-12" >--}%
                                <blockquote class="blockquote-small">
                                    <p>
                                    ${comment.content}
                                    </p>
                                    <footer>
                                        by ${comment.user.name} on <g:formatDate format="EEE, d MMM yyyy" date="${comment.created}" />
                                    </footer>
                                </blockquote>
                            </div>
                            <div class="col-sm-12 div-divider" ></div>
                        </g:each>
                        <g:form class="form-horizontal" url="${g.createLink(controller: 'task', action: 'postComment')}">
                            <g:hiddenField name="task" value="${g.fieldValue(bean: task, field: 'id')}" />
                            <div class="row clearfix">
                                <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4  col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <g:textArea name="content" class="form-control no-resize" required="required" placeholder="Type new comment here"
                                                        value="${fieldValue(bean: comment, field: 'content')}" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row clearfix">
                                <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                                    <button type="submit" class="btn btn-primary m-t-15 waves-effect" >
                                        <i class="material-icons">comment</i>
                                        <span>Post Comment</span>
                                    </button>
                                </div>
                            </div>
                        </g:form>
                        </g:if>
                        <g:if test="${!task.id}">
                            Create a task first to post comments
                        </g:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>