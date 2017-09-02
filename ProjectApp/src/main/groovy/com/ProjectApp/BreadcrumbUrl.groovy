package com.ProjectApp

class BreadcrumbUrl  {

    String title
    String url
    String icon

    static rootUrlTitle = [
            "Projects",
            "Profile",
            "Tasks"
    ]

    Boolean isRootUrl() {
        return rootUrlTitle.contains(title)
    }

    boolean equals(BreadcrumbUrl bu) {
        return this.title == bu.title
    }

    String toString() {
        return "${title}"
    }

}
