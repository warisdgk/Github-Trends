package mwaris.dev.githubtrends.testdoubles

import mwaris.dev.githubtrends.data.entities.Repository

object DummyRepositoriesData {
    val listOfTrendingRepositories = listOf(
        Repository(
            "23096959",
            "go",
            "golang/go",
            "https://avatars.githubusercontent.com/u/4314092?v=4",
            "The Go programming language",
            "15643",
            "Python"
        ),
        Repository(
            "34526884",
            "ant-design",
            "ant-design/ant-design",
            "https://avatars.githubusercontent.com/u/12101536?v=4",
            "An enterprise-class UI design language and React UI library",
            "15643",
            "Python"
        ),
        Repository(
            "44838949",
            "swift",
            "apple/swift",
            "https://avatars.githubusercontent.com/u/10639145?v=4",
            "The Swift Programming Language",
            "15643",
            "Python"
        ),
        Repository(
            "3638964",
            "ansible",
            "ansible/ansible",
            "https://avatars.githubusercontent.com/u/1507452?v=4",
            "Ansible is a radically simple IT automation platform that makes your applications and systems easier to deploy and maintain. Automate everything from code deployment to network configuration to cloud management, in a language that approaches plain English, using SSH, with no agents to install on remote systems. https://docs.ansible.com.",
            "15643",
            "Python"
        ),
    )
}