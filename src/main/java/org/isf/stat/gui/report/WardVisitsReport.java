/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2006-2020 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
 *
 * Open Hospital is a free and open source software for healthcare data management.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.isf.stat.gui.report;

import java.util.Date;

import javax.swing.JOptionPane;

import org.isf.generaldata.GeneralData;
import org.isf.generaldata.MessageBundle;
import org.isf.menu.manager.Context;
import org.isf.stat.dto.JasperReportResultDto;
import org.isf.stat.manager.JasperReportsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.view.JasperViewer;

public class WardVisitsReport {
	   private final Logger logger = LoggerFactory.getLogger(GenericReportPatient.class);

		public WardVisitsReport(String string, Date date, String jasperFileName) {
			try{
	            JasperReportsManager jasperReportsManager = Context.getApplicationContext().getBean(JasperReportsManager.class);
	            JasperReportResultDto jasperReportResultDto = jasperReportsManager.getGenericReportWardVisitPdf(string, date, jasperFileName);
				if (GeneralData.INTERNALVIEWER)
					JasperViewer.viewReport(jasperReportResultDto.getJasperPrint(),false);
				else { 
						Runtime rt = Runtime.getRuntime();
						rt.exec(GeneralData.VIEWER +" "+ jasperReportResultDto.getFilename());
				}
			} catch (Exception e) {
	            logger.error("", e);
	            JOptionPane.showMessageDialog(null, MessageBundle.getMessage("angal.stat.reporterror"), MessageBundle.getMessage("angal.hospital"), JOptionPane.ERROR_MESSAGE);
			}
		}
}
