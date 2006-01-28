package net.jsunit.plugin.eclipse.resultsui;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.ui.IViewSite;

public class AllTestsByBrowserResultsTab extends HierarchyTestResultsTab {

	public AllTestsByBrowserResultsTab(CTabFolder tabFolder, IViewSite viewSite, ContentProvider contentProvider, FailureTrace failureTrace) {
		super(tabFolder, viewSite, contentProvider, failureTrace);
	}

	protected String getToolTipText() {
		return "Shows all test results, arranged by browser";
	}

	public String getName() {
		return "Browser Hierarchy";
	}

	protected String getImageName() {
		return "testhier.gif";
	}

}