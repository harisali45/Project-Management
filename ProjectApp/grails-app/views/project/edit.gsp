<html>
<head>
    <meta name="layout" content="main" />
    <title>Projects</title>
</head>
<body>
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                {project.title}
                <small>Project Details</small>
            </div>
        </div>
        <div class="body">
            <g:form class="form-horizontal" >
                <label for="title">Title</label>
                <div class="form-group">
                    <div class="form-line">
                        <input type="text" id="title"  name="title" class="form-control" placeholder="Enter project title"
                        value="${fieldValue(bean: project, field: 'title')}" />
                    </div>
                </div>
                <label for="description">Description</label>
                <div class="form-group">
                    <div class="form-line">
                        <textarea type="text" id="description" name="description" class="form-control no-resize" placeholder="Enter project title"
                               value="${fieldValue(bean: project, field: 'description')}" rows="3" cols="20"/>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>