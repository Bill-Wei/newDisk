package swun.iot.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


import com.opensymphony.xwork2.ActionSupport;

public class ValidationCodeAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	
//	封装ServletManager对象的属性
	private HttpServletRequest request;
	private HttpServletResponse response;
	
//	实现setServletResponse方法，获得HttpServletResponse对象
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
//	实现setServletRequest方法，获得HttpServletRequest对象
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
//	图形验证码的字符集合，系统将随机从这几个字符串中选择一些字符作为验证码
	String codeChars = "%#01223456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
//	返回一个随机颜色（color对象）
	private Color getRandomColor(int minColor,int maxColor){
		Random random = new Random();
		//保存minColor最大不会超过255
		if (minColor>255) {
			minColor = 255;
		}
		//保存maxColor最大不会超过255
		if (maxColor>255) {
			maxColor = 255;
		}
		//获得红色的颜色随机值
		int red = minColor+random.nextInt(maxColor-minColor);
		//获得绿色颜色的随机值
		int green = minColor+random.nextInt(maxColor-minColor);
		//获得蓝色颜色的随机值
		int blue = minColor+random.nextInt(maxColor-minColor);
		return new Color(red, green, blue);
	}
	@Override
	public String execute() throws Exception {
		//获得验证码集合长度
		int charsLength = codeChars.length();
		
		//下面三条记录是关闭客户端浏览器的缓冲区
		//这三条语句都可以关闭浏览器的缓冲区，但是由于浏览器的版本不同，对这三条语句的支持也不同
		//因此，为了保险起见，建议同时用这三条语句来关闭浏览器的缓冲区
		response.setHeader("ragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//设置图形验证码的长和宽（图形的大小）
		int width = 90,height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		
		Graphics g = image.getGraphics();//获得用于输出文字的Graphics对象 
		Random random = new Random();
		g.setColor(getRandomColor(180, 250));//随机设置要填充的颜色
		g.fillRect(0, 0, width, height);//填充图形背景
		//设置初始字体
		g.setFont(new Font("Times New Roman", Font.ITALIC, height));
		
		g.setColor(getRandomColor(120, 180));//随机设置字体颜色
		//用于保存最后随机生成的验证码
		StringBuilder validationCode = new StringBuilder();
		//验证码的随机字体
		String[] fontNames = {"Times New Roman","Book antiqua","Arial"};
		//随机生成3到5个验证码
		for (int i = 0; i < 3+random.nextInt(3); i++) {
			//随机设置当前验证码的字符的字体
			g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height));
			//随机获得当前验证码的字符
			char codeChar = codeChars.charAt(random.nextInt(charsLength));
			validationCode.append(codeChar);
			//随机设置当前验证码字符的颜色
			g.setColor(getRandomColor(10, 100));
			//在图形上画出验证码字符，x和y都是随机生成的
			g.drawString(String.valueOf(codeChar), 16*i+random.nextInt(7), height-random.nextInt(6));
			
		}
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(5*60);//设置session对象5分钟失效
//		将验证码保存在session中，key为validation_code
		session.setAttribute("validation_code", validationCode.toString());
		
		g.dispose();//关闭graphics对象
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "JPEG", os);//以JPEG的格式想客户端发送图形验证码
		return null;
	}
	
	
	
	
}
