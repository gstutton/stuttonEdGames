package uk.co.stutton.games.question.mathsy1.gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * Our custom JButton class that is Draggable. This JButton is Transferable (so
 * it can be Dragged), And listens for its own drags
 * 
 * @see http
 *      ://www.dreamincode.net/forums/topic/209966-java-drag-and-drop-tutorial
 *      -part-1-basics-of-dragging/
 * 
 * */
public class DragAndDropButton extends JButton implements Transferable,
		DragSourceListener, DragGestureListener {

	// marks this JButton as the source of the Drag
	private DragSource source;

	private TransferHandler t;

	public DragAndDropButton() {
		this("");
	}

	public DragAndDropButton(String message) {
		super(message);

		// The TransferHandler returns a new DnDButton
		// to be transferred in the Drag
		t = new TransferHandler() {

			public Transferable createTransferable(JComponent c) {
				return new DragAndDropButton(getText());
			}
		};
		setTransferHandler(t);

		// The Drag will copy the DnDButton rather than moving it
		source = new DragSource();
		source.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_MOVE, this);
	}

	// The DataFlavor is a marker to let the DropTarget know how to
	// handle the Transferable
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { new DataFlavor(DragAndDropButton.class,
				"JButton") };
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return true;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) {
		return this;
	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
	}

	@Override
	public void dragExit(DragSourceEvent dse) {
	}

	// when the drag finishes, then repaint the DnDButton
	// so it doesn't look like it has still been pressed down
	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {
		repaint();
	}

	// when a DragGesture is recognized, initiate the Drag
	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		source.startDrag(dge, DragSource.DefaultMoveDrop,
				new DragAndDropButton("Text"), this);
	}

}// end outer class

