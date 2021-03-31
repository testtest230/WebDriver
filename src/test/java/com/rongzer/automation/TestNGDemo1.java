package com.rongzer.automation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestNGDemo1 {
    //声明webdriver
    WebDriver webdriver;

    @BeforeMethod
    public void beforeMethod() {
//        System.setProperty("webdriver.firefox.marionette","E:\\Automation\\Project1\\src\\drivers\\geckodriver.exe");
//        webdriver = new FirefoxDriver();
        webdriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
//        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        webdriver.get("http://www.baidu.com");
//        webdriver.get("https://www.bejson.com/jsonviewernew/");
//        webdriver.get("https://mail.163.com/");

        System.out.println("ee");

    }

    /**
     * 打开浏览器
     */
    @Test
    public void openExplore(){
        webdriver.get("http://www.baidu.com/");
    }

    /**
     * 练习基本操作
     */
    @Test
    public void basicOperation() throws InterruptedException {
        //打开某个网页
        webdriver.get("http://www.baidu.com");
//        //在当前网页跳到其他网页
//        webdriver.navigate().to("http://youdao.com");
//        Thread.sleep(3000);
//        //后退
//        webdriver.navigate().back();
//        webdriver.navigate().back();
//        Thread.sleep(3000);
//        //前进
//        webdriver.navigate().forward();
//        Thread.sleep(3000);
//        //当前窗口刷新操作
//        webdriver.navigate().refresh();
//        Thread.sleep(3000);
//        //当前窗口最大化
//        webdriver.manage().window().maximize();
//        Thread.sleep(3000);
//        //指定窗口的大小
//        Dimension dimension = new Dimension(200,200);
//        webdriver.manage().window().setSize(dimension);
        System.out.println("当前窗口的url:" + webdriver.getCurrentUrl());
        System.out.println("当前窗口的title:" + webdriver.getTitle());
        //获取元素
        WebElement webElement = webdriver.findElement(By.id("kw"));
        webElement.sendKeys("jdk");
        Thread.sleep(3000);
        System.out.println("获取目前输入的文本框的内容:" + webElement.getText());
        System.out.println("获取目前输入的文本框的标签名:" + webElement.getTagName());
        System.out.println("获取目前输入的文本框的name属性名:" + webElement.getAttribute("name"));
        System.out.println("获取一个文本框的内容:" + webdriver.findElement(By.xpath("//*[@id=\"form\"]/span[1]/span[2]")).getText());
        webElement.clear();



    }

    /**
     * 截图
     */
    @Test
    public void takePicture() {
        File file = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(new File(ARCHIVE_DIR + "fyensdk.arc"),file);
    }

    /**
     * 转交控制权到alert、confirm、promot
     */
    @Test
    public void alert() {
        webdriver.get("C:\\Users\\Administrator\\Desktop\\111.html");
        //找到某个元素，然后点击它
        webdriver.findElement(By.id("hahah")).click();
        //把控制权转交给这个alert
        Alert alert = webdriver.switchTo().alert();
        //点击确定按钮
        alert.accept();
    }

    /**
     * 下拉框的操作
     */
    @Test
    public void selectTest() throws InterruptedException {
        webdriver.get("C:\\Users\\Administrator\\Desktop\\111.html");
        //找到下拉框元素
        webdriver.findElement(By.id("contactRelationship")).click();
        //实例化select
        Select select = new Select(webdriver.findElement(By.id("contactRelationship")));
//        Thread.sleep(3000);
//        select.selectByIndex(3);
        //获取第一个select的文本框
        String s = select.getFirstSelectedOption().getText();
        System.out.println("第一个select元素的文本框:" + s);
        //获取所有的下拉框
        List<WebElement> allOptions = select.getOptions();
        for (WebElement webElement : allOptions) {
            //打印每个下拉框的文本值
            System.out.println("打印每个下拉框的文本值:" + webElement.getText());
        }
    }

    /**
     * 测试多个窗口【话柄】
     */
    @Test
    public void handles() {
        webdriver.get("http://www.baidu.com");
        String handle = webdriver.getWindowHandle();
        System.out.println("获取当前打开窗口的话柄:" + handle);
        webdriver.findElement(By.xpath("//*[@id=\"s-top-left\"]/a[1]")).click();
        for (String handles : webdriver.getWindowHandles()) {
            //获取当前所有窗口的话柄
            if (handles.equals(handle)) {
                continue;
            }
            //将当前窗口的话柄转交新打开的窗口
            webdriver.switchTo().window(handles);
        }
        //控制权交到最新的窗口，获取元素进行操作
        webdriver.findElement(By.linkText("军事")).click();
    }

    /**
     * Actions测试【右击、双击等】
     * 这些操作必须 执行perform()才能生效
     */
    @Test
    public void actionsTest() {
        webdriver.get("http://www.baidu.com");
        Actions actions = new Actions(webdriver);
        System.out.println("actions:" + actions);
//        WebElement webElement = webdriver.findElement(By.id("kw"));
//        //右击
//        actions.contextClick(webElement).perform();
        //获取某个元素
        WebElement webElement = webdriver.findElement(By.linkText("更多"));
        actions.moveToElement(webElement).perform();
    }

    /**
     * js脚本的测试
     */
    @Test
    public void jsTest() {
        webdriver.get("file:///C:/Users/Administrator/Desktop/111.html");
        //获取到某个元素
//        WebElement webElement = webdriver.findElement(By.xpath("/html/body/div[2]"));
//        ((JavascriptExecutor)webdriver).executeScript("arguments[0].setAttribute('style', arguments[1])", webElement, "width:200px;height:200px;background: yellow;");
        WebElement webElement = webdriver.findElement(By.id("hahah"));
//        ((JavascriptExecutor) webdriver).executeScript("arguments[0].setAttribute('onclick',arguments[1])", webElement, "function aa(){ alert('This is my alert!');}");
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].onclick=function(){alert('aa')}", webElement);
        webElement.click();
//        ((JavascriptExecutor) webdriver).executeScript("arguments[0].click()", webElement);

    }

    /**
     * 登录邮箱
     *
     * @param username
     * @param pwd
     */
    @Test
    public void login(String username, String pwd) {
        //1、打开企业邮箱
        webdriver.get("https://exmail.qq.com/cgi-bin/loginpage?s=session_timeout&from=&r=9b4979c24ec90429d813d55d69c11f91");
        //2、点击账号密码登录
        webdriver.findElement(By.linkText("帐号密码登录")).click();
        //3、输入用户名
        webdriver.findElement(By.id("inputuin")).sendKeys(username);
        //4、输入密码
        webdriver.findElement(By.id("pp")).sendKeys(pwd);
//        //5、勾选下面的复选框
//        webdriver.findElement(By.id("auto_login_in_five_days_pwd")).click();
        webdriver.findElement(By.id("btlogin")).click();
    }

    /**
     * 发送电子邮件
     */
    @Test
    public void sendEmail() {
        //1、登录
        login("wang.wei01@rongzer.com", "Ww4141183041");
        System.out.println("登录成功!");
        //2、点击写信按钮
        webdriver.findElement(By.id("composebtn_td")).click();
        //3、转交控制权到frame
        WebElement element = webdriver.findElement(By.id("mainFrame"));
        webdriver.switchTo().frame(element);
//        webdriver.switchTo().frame("mainFrame");
        //4、收件人
//        webdriver.findElement(By.xpath("html/body/form[2]/div[3]/div[1]/table[2]/tbody/tr/td[2]/div[1]/div[2]/input")).sendKeys("wang.wei01@rongzer.com");
        webdriver.findElement(By.cssSelector("#toAreaCtrl .addr_text [type='input']")).sendKeys("wang.wei01@rongzer.com");
        //5、邮件主题
        webdriver.findElement(By.xpath("//*[@title=\"主题是一封邮件的标题，可不填。\"]")).sendKeys("哈哈哈");
        //6、嵌套的iframe  再次转交控制权
        WebElement webElement = webdriver.findElement(By.xpath("html/body/form[2]/div[3]/div[4]/table/tbody/tr/td[2]/div[1]/table/tbody/tr[2]/td/iframe"));
        webdriver.switchTo().frame(webElement);
        WebElement web = webdriver.findElement(By.xpath("//*[@id=\"AttachFrame\"]/span"));
        Alert alert = webdriver.switchTo().alert();
        //sendKeys("C:\\Users\\Administrator\\Desktop\\1.jpg")
        //7、邮件正文
        webdriver.findElement(By.xpath("/html/body/div[1]")).sendKeys("这是测试的东西");
        //8、将控制权转到最初的webdriver【刚进来时的webdriver】
        webdriver.switchTo().defaultContent();
        //9、再次将控制权交到发送按钮的webdriver
        webdriver.switchTo().frame("mainFrame");
        //10、点击发送
        webdriver.findElement(By.xpath("//*[@id=\"toolbar_bottom\"]/div/div/input")).click();
    }

    /**
     * 删除电子邮件
     */
    @Test
    public void delEmail() {
        //1、登录
        login("wang.wei01@rongzer.com", "Ww4141183041");
        System.out.println("登录成功!");
        //2、点击草稿箱按钮
//        webdriver.findElement(By.xpath("/html/body[@class='frame_class']/div[@id='resize']/div[@id='leftPanel']/div[@id='navMidBar']/div[@id='folder']/div/div[@id='OutFolder']/div[@id='SysFolderList']/ul[@class='fdul']/li[@id='folder_4_td']/a[@id='folder_4']")).click();;
        webdriver.findElement(By.id("folder_4")).click();
        ;
        //3、转交控制权到iframe
        webdriver.switchTo().frame("mainFrame");
//        WebElement frameElment = webdriver.findElement(By.id("mainFrame"));
//        System.out.println("frameElment:"+frameElment);
//        webdriver.switchTo().frame(frameElment);
        //4、执行js 勾选复选框
        WebElement eb = webdriver.findElement(By.xpath("//*[@class=\"toarea\"]/table"));
        ((JavascriptExecutor) webdriver).executeScript("document.getElementsByClassName(\"i M\")[0].setAttribute(\"class\",\"i M B\")", eb);
        ((JavascriptExecutor) webdriver).executeScript("document.getElementsByName(\"mailid\")[0].checked = true", eb);
        //5、点击删除草稿按钮
        webdriver.findElement(By.xpath("//*[@id=\"quick_del\"]")).click();
        //6、弹框确认是否删除，将控制权转交
        webdriver.switchTo().defaultContent();
        //7
        webdriver.findElement(By.id("QMconfirm_s_cancel")).click();


//        var a= document.getElementById("auto_login_in_five_days_pwd");
//        a.setAttribute("checked",true);

    }

    @Test
    public void checkJson() {
        webdriver.findElement(By.id("edit")).sendKeys("{\"data\":[{\"professionCode\":\"A001\",\"professionName\":\"电力/热力/燃气及水生产/供应业\"},{\"professionCode\":\"A002\",\"professionName\":\"农/林/牧/渔业\"},{\"professionCode\":\"A003\",\"professionName\":\"金融业\"},{\"professionCode\":\"A004\",\"professionName\":\"交通运输/仓储/邮政业\"},{\"professionCode\":\"A005\",\"professionName\":\"水利/环保\"},{\"professionCode\":\"A006\",\"professionName\":\"采矿业\"},{\"professionCode\":\"A007\",\"professionName\":\"信息传输/软件/信息技术服务业\"},{\"professionCode\":\"A008\",\"professionName\":\"科技研究/技术服务业\"},{\"professionCode\":\"A009\",\"professionName\":\"制造业\"},{\"professionCode\":\"A010\",\"professionName\":\"居民服务/修理/其他服务业\"},{\"professionCode\":\"A011\",\"professionName\":\"卫生/社会工作\"},{\"professionCode\":\"A012\",\"professionName\":\"房地产业\"},{\"professionCode\":\"A013\",\"professionName\":\"公共管理/社会保障/社会组织\"},{\"professionCode\":\"A014\",\"professionName\":\"文化/体育/娱乐业\"},{\"professionCode\":\"A015\",\"professionName\":\"建筑业\"},{\"professionCode\":\"A016\",\"professionName\":\"租赁/商务服务业\"},{\"professionCode\":\"A017\",\"professionName\":\"批发/零售业\"},{\"professionCode\":\"A018\",\"professionName\":\"教育\"},{\"professionCode\":\"A019\",\"professionName\":\"住宿/餐饮业\"},{\"professionCode\":\"A020\",\"professionName\":\"国际组织\"},{\"professionCode\":\"A021\",\"professionName\":\"其他\"}],\"status\":1}");
        webdriver.findElement(By.id("ext-gen37")).click();
        ////*[@id="ext-gen145"]/li/div
        WebElement webElement = webdriver.findElement(By.xpath("//*[@id=\"ext-gen145\"]/li/div"));
        String js = "document.";
//        ((JavascriptExecutor)webdriver).executeScript()
        //x-tree-node-el x-unselectable  x-tree-node-collapsed
        //x-tree-node-el x-unselectable  x-tree-node-expanded
        webdriver.findElement(By.className("x-tree-ec-icon x-tree-elbow-end-minus")).click();
    }

    /**
     * 百度首页的文件上传
     */
    @Test
    public void uploadFile() {
//        1、点击按图片搜索的按钮
        webdriver.findElement(By.className("soutu-btn")).click();
        //2、文件上传
        webdriver.findElement(By.className("upload-pic")).sendKeys("C:\\Users\\Administrator\\Desktop\\1.jpg");
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        Thread.sleep(3000);
//        webdriver.quit();
    }
}