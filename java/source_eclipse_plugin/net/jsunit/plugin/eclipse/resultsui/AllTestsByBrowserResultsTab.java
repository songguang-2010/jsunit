package net.jsunit.plugin.eclipse.resultsui;

import net.jsunit.model.TestCaseResult;
import net.jsunit.plugin.eclipse.JsUnitPlugin;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;

public class AllTestsByBrowserResultsTab extends TestResultsTab {

	private TreeViewer treeViewer;

	public AllTestsByBrowserResultsTab(CTabFolder tabFolder, IViewSite viewSite, ContentProvider contentProvider, FailureTrace failureTrace) {
		super(tabFolder, viewSite, contentProvider, failureTrace);
	}

	protected void addControlToPanel(IViewSite viewSite, ContentProvider contentProvider, Composite panel) {
		treeViewer = new TreeViewer(panel, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gridData= new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		treeViewer.getControl().setLayoutData(gridData);

		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(new ViewLabelProvider());
		treeViewer.setSorter(null);
		treeViewer.setInput(viewSite);
	}

	protected String getToolTipText() {
		return "Shows all test results, arranged by browser";
	}

	public String getName() {
		return "Hierarchy";
	}

	public void refresh() {
		JsUnitPlugin.getDisplay().syncExec(new Runnable() {
			public void run() {
				treeViewer.refresh();
			}
		});		
	}

	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	protected void hookSelectionChangedEventTo(final Action action) {
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				action.run();
			}
		});		
	}

	protected TestCaseResult getSelectedTestCaseResult() {
		IStructuredSelection selectionList = (IStructuredSelection) treeViewer.getSelection();
		Node node = (Node) selectionList.getFirstElement();
		if (node instanceof TestCaseResultNode)
			return ((TestCaseResultNode) node).getResult();
		return null;
	}

	protected String getImageName() {
		return "testhier.gif";
	}

}