package com.github.iamsiddharath.captureme.handlers;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

public class CaptureMeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			boolean flag = MessageDialog.openConfirm(window.getShell(), "Capture Me",
					"Do you want to capture the screen ? ");

			if (flag) {
				Thread.sleep(1000);
				Robot r = new Robot(); 
				String filePath = System.getProperty("user.home")+"/Desktop/Screen-capture/";
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_sss");
				
				File folder = new File(filePath);
				
				if(!folder.exists()) {
					folder.mkdir();
				}
				
				
	            String path = filePath+"Shot_"+sdf.format(new Date())+".jpg"; 
	  
	            Rectangle capture =  
	            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
	            BufferedImage Image = r.createScreenCapture(capture); 
	            ImageIO.write(Image, "jpg", new File(path));
	            MessageDialog.openInformation(window.getShell(), "Capture Me",
						"Success! Screenshot is captured \n\n Location of Screen shot : "+path);
			}
		} catch (Exception e) {
		}

		return null;
	}
}
