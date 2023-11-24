let url_config  = ""
 
if (process.env.NODE_ENV === 'development'){
    // 开发环境
    url_config = "http://localhost:9998";
	// url_config = "https://owentest.mynatapp.cc";
	// url_config = "http://vs3z8v.natappfree.cc"; 
} else {
    // 生产环境
    // url_config = "http://117.50.198.39:9998";
	url_config = "https://owentest.mynatapp.cc";
}
 
export default url_config 