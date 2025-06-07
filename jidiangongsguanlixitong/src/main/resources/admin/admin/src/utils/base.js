const base = {
    get() {
        return {
            url : "http://localhost:8080/jidiangongsguanlixitong/",
            name: "jidiangongsguanlixitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/jidiangongsguanlixitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "机电公司管理信息系统"
        } 
    }
}
export default base
