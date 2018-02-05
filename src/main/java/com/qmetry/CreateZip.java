/*******************************************************************************
* Copyright 2017 Infostretch Corporation
*
* This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
*
* IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
* OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
* OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE
*
* You should have received a copy of the GNU General Public License along with this program in the name of LICENSE.txt in the root folder of the distribution. If not, see https://opensource.org/licenses/gpl-3.0.html
*
*
* For any inquiry or need additional information, please contact qmetrysupport@infostretch.com
*******************************************************************************/
package com.qmetry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream; 

public class CreateZip
{
	public static String createZip(String filepath,String format) throws FileNotFoundException,IOException
	{	
		String extention="";
		if(format.equals("testng/xml") || format.equals("junit/xml"))
		{
			extention="xml";
		}
		else if(format.equals("qas/json") || format.equals("cucumber/json")) 
		{
			extention="json";
		}
		File f = new File(filepath+"/"+"testresult.zip");
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(f));
		File file=new File(filepath);
		File[] farray=file.listFiles();
		String filename;
		if(farray!=null)
		{	
			for(File f1:farray)
			{
				filename=f1.getName();
				if(filename.endsWith(extention))
				{
					ZipEntry e = new ZipEntry(filename);
					zos.putNextEntry(e);
					FileInputStream fis=null;
					try
					{
						fis = new FileInputStream(f1);
						byte[] bytes = new byte[1024];
						int length;
						
						while ((length = fis.read(bytes)) >= 0)
						{
							zos.write(bytes, 0, length);
						}
					}
					finally
					{
						if(fis!=null)
							 fis.close();
						zos.closeEntry();
					}
				}
			}
		}
		zos.close();
		return filepath+"/"+"testresult.zip";
	}
}