# -*-coding:utf-8 -*-
__author__ = 'jiangtao'

import urllib
import urllib2
import time
from bs4 import BeautifulSoup


#易高考网站的各省历年批次线API如下：
#http://www.easygaokao.com/api/school/getResidenceCutoff?district=%E5%8C%97%E4%BA%AC&category=%E7%90%86%E7%A7%91&year=2016&ItemCountPerpage=50&page=1

#输入参数为arg，arg = [ ]，是带有4个参数的列表。参数如下：
#arg1：省份， arg2：文理科， arg3：年份， arg4：目前为止已成功爬取的个数，定义了开始爬取的起点参数
#函数返回值也为List，当程序执行中页面没爬完就遇到异常，则返回同样的参数[省份，文理科，年份，目前为止已成功爬取的个数]，以供下次递归时接着爬
#或程序无异常的爬到最后结束，则只返回含有一个元素，字符串'OK'的List，即['OK'],以此来判断是否需要递归
def Craw_YGK_ResidenceCutoff(arg):

    #爬虫地址url和header，GET方法
    url = "http://www.easygaokao.com/api/school/getResidenceCutoff"
    header = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36'}

    #以下为url的参数
    province = ["北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南",
           "湖北","湖南","广东","海南","四川","贵州","云南","陕西","甘肃","青海","内蒙古","广西","西藏","宁夏","新疆"]

    categorys = ['理科','文科']

    years = [2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016]

    fw_count = arg[3]  #变量fw_count 为成功爬取的计数器，成功一次+1

    continue_flag = 1  #continue的标记，如果在所传入的url参数arg之前的已经爬取过，则continue掉

    #判断传入的url参数,如果全为None则代表是首次爬取,将URL参数设置为上述各List的第一个元素
    if arg[0] is None and arg[1] is None and arg[2] is None:
        arg[0] = province[0]
        arg[1] = categorys[0]
        arg[2] = years[0]

    #成功爬取后以JSON格式存文件
    fw = open("E:\CollegeScoreLibrary\MyBigData\YiGaoKao\YGK_batchscore_all.json","a")
    for district in province:
        for category in categorys:
            for year in years:
                for page in range(1, 2):

                    #在循环中判断此时的参数是否等于所传入的arg所代表的页面爬取的起点，若是起点，改变continue的标识。
                    if district==arg[0] and category==arg[1] and year==arg[2]:
                        continue_flag = 0

                    #如果到起点了，continue的标识为0,（不continue了），则开始爬
                    if not continue_flag:
                        paramdata = 'district='+district+'&category='+category+'&year='+str(year)+'&ItemCountPerpage=50&page='+str(page)
                        urlGET = url+'?'+paramdata
                        #proxy = getUserAgent()     #不使用代理，因为暂时找不到免费的
                        #html = gethtmlByAgent(url, None, header, proxy)

                        try:
                            html = gethtml(urlGET, None, header)
                            if html is not None:
                                json = eval(html)
                                item_data = json.get('data').get('item')
                                if item_data is not None and len(item_data) > 0:
                                    fw_count += 1   #记录到目前为止爬取的成功的个数
                                    print fw_count, '. ', district, '  ', category, '  ', year, '  OK!' #爬的时候用于打印当前状态

                                    #开始写文件，每个爬到的JSON数据之间用‘,\n’隔开
                                    fw.write(html)
                                    fw.write(',\n')   #每个爬取的原始数据段之间用',\n'隔开，方便之后的处理

                                else:

                                    #若出现错误则记录该错误处并存文件
                                    fw_error = open("E:\CollegeScoreLibrary\MyBigData\YiGaoKao\YGK_batchscore_all_ERROR.txt","a")
                                    fw_error.write('Error  '+district+'  '+category+ '  '+str(year)+ '\n')
                                    fw_error.close()

                            else:
                                fw_error = open("E:\CollegeScoreLibrary\MyBigData\YiGaoKao\YGK_batchscore_all_ERROR.txt","a")
                                fw_error.write('Error  '+district+'  '+category+ '  '+str(year)+ '\n')
                                fw_error.close()

                                #记录该次未成功爬取时的url参数，下一个递归时再从这里开始爬
                                last_error_args = [district, category, year, fw_count]
                                return last_error_args

                        except Exception,e:
                            fw_error = open("E:\CollegeScoreLibrary\MyBigData\YiGaoKao\YGK_batchscore_all_ERROR.txt","a")
                            fw_error.write('Error  '+district+'  '+category+ '  '+str(year)+ '\n')
                            fw_error.close()

                            #记录该次未成功爬取时的url参数，下一个递归时再从这里开始爬
                            last_error_args = [district, category, year, fw_count]
                            return last_error_args

                        #经测试，易高考网站对同一IP限制了访问频率，固每爬完一个页面在此处作1秒的休眠，可看实际情况设置该值
                        time.sleep(1)

    fw.close()
    return ['OK']   #所有页面爬取成功，返回‘OK’


#该函数用于构造爬虫的递归，一直执行到最后全部爬完才停止
def Craw_YiGaoKao(args):
    return_args = Craw_YGK_ResidenceCutoff(args)
    if len(return_args) == 4:
        Craw_YiGaoKao(return_args)
    elif len(return_args)==1:
        print '爬取结束！'


#通过代理IP爬取网页
def gethtmlByAgent(url,postdata, header,userAgent):
    #设置使用代理
    proxy = userAgent
    proxy_support = urllib2.ProxyHandler(proxy)
    # opener = urllib2.build_opener(proxy_support,urllib2.HTTPHandler(debuglevel=1))
    opener = urllib2.build_opener(proxy_support)
    urllib2.install_opener(opener)

    request = urllib2.Request(url, postdata, headers = header)
    response = urllib2.urlopen(request, timeout=100)
    if response is not None:
        html = response.read()
        if html is not None:
            return html
        return None
    return None


#不通过代理IP爬取网页
def gethtml(url,postdata, header):
    request = urllib2.Request(url, postdata, headers = header)
    response = urllib2.urlopen(request, timeout=1000)
    if response is not None:
        html = response.read()
        if html is not None:
            return html
        return None
    return None


#使用代理IP时调用此方法，从代理IP文件中获取IP和端口
def getUserAgent():
    fr = open("E:\CollegeScoreLibrary\MyBigData\UserAgent\AgentFromXicidaili.txt","r")
    contents = fr.read()[:-2]   #去除文件后面的',\n'
    fr.close()
    proxys = contents.split(',\n')

    url = "http://ip.chinaz.com/getip.aspx"
    for proxy in proxys:
        testAgent = {'http': proxy}
        try:
            res = urllib.urlopen(url, proxies=testAgent).read()
            print res
            break
        except Exception,e:
            print proxy
            print e
            continue

#从免费的代理网站获取代理IP和端口
def download_userAgent():
    User_Agent = 'Mozilla/5.0 (Windows NT 6.3; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0'
    header = {}
    header['User-Agent'] = User_Agent

    #从免费的代理网站获取IP和端口
    url = 'http://www.xicidaili.com/nn/1'
    req = urllib2.Request(url,headers=header)
    res = urllib2.urlopen(req).read()
    soup = BeautifulSoup(res, 'html.parser', from_encoding='utf-8')

    #先看是否已经存了代理IP
    fr = open("E:\CollegeScoreLibrary\MyBigData\UserAgent\AgentFromXicidaili.txt","r")
    if fr.read() is None:
        fw = open("E:\CollegeScoreLibrary\MyBigData\UserAgent\AgentFromXicidaili.txt","a")
        ips = soup.findAll('tr')
        for x in range(1,len(ips)):
            all_Ip = ips[x]
            tds = all_Ip.findAll("td")
            ip_temp = tds[1].contents[0]+":"+tds[2].contents[0]
            fw.write(ip_temp+',\n')
        fw.close()
    else:
        print 'File Content is exist! '
    fr.close()


#测试
def test():
    url = "http://www.easygaokao.com/api/school/getResidenceCutoff"#?district=%E5%8C%97%E4%BA%AC&category=%E7%90%86%E7%A7%91&year=2016&ItemCountPerpage=50&page=1"
    header = {'User-Agent': 'Mozilla/5.0'}
    paramdata = 'district=%E5%8C%97%E4%BA%AC&category=%E7%90%86%E7%A7%91&year=2016&ItemCountPerpage=50&page=1'
    request = urllib2.Request(url+'?'+paramdata, headers=header)   #因为是GET方法，用POST方法被服务器拒绝
    response = urllib2.urlopen(request, timeout=100)
    html = response.read()
    print html




#程序入口main方法
if __name__ == "__main__":
    test1 = 0
    args = [None, None, None, 0]
    Craw_YiGaoKao(args)


