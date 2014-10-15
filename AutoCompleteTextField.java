import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;


public class AutoCompleteTextField extends JTextField 
{
	private WordManager manager;
	private static final String COMMIT_ACTION = "commit";
	private static enum Mode { INSERT, COMPLETION };
	private Mode mode;
	
	public AutoCompleteTextField(String text,int length)
	{
		super(text,length);
		manager = new WordManager();
		this.getDocument().addDocumentListener(new TypeListener());
		mode = Mode.INSERT;
		InputMap im = this.getInputMap();
        ActionMap am = this.getActionMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), COMMIT_ACTION);
        am.put(COMMIT_ACTION, new CommitAction());
	}
	
	public void addWords(String[] words)
	{
		for (int i=0;i<words.length;i++)
		{
			manager.addWord(words[i]);
		}
	}
	
	private class TypeListener implements DocumentListener
	{
		@Override
		public void insertUpdate(DocumentEvent ev) 
		{
			// TODO Auto-generated method stub
			
			
			if (ev.getLength() != 1) 
			{
	            return;
	        }
	         
	        int pos = ev.getOffset();
	        String content = null;
	        try 
	        {
	            content = AutoCompleteTextField.this.getText(0, pos + 1);
	        } 
	        catch (BadLocationException e) 
	        {
	            e.printStackTrace();
	        }
	         
	        // Find where the word starts
	        int w;
	        for (w = pos; w >= 0; w--) {
	            if (! Character.isLetter(content.charAt(w))) {
	                break;
	            }
	        }
	        if (pos - w < 2) {
	            // Too few chars
	            return;
	        }
	        String str = content.substring(w + 1);
	         
	        ArrayList<String>words = manager.getWords(str);
	        
	        if (words.size()>0) 
	        {
	        	int i;
	            String match = words.get(0);
	            i = 0;
	            while ((i<words.size()-1) && !match.startsWith(str))
	            {
	            	i++;
	            	match = words.get(i);
	            }
	            if (match.startsWith(str)) 
	            {
	                // A completion is found
	                String completion = match.substring(pos - w);
	                // We cannot modify Document from within notification,
	                // so we submit a task that does the change later
	                SwingUtilities.invokeLater(
	                        new CompletionTask(completion, pos + 1));
	            }
	        } else {
	            // Nothing found
	            mode = Mode.INSERT;
	        }
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

				
	}
	
	 private class CompletionTask implements Runnable 
	 {
	        String completion;
	        int position;
	         
	        CompletionTask(String completion, int position) 
	        {
	            this.completion = completion;
	            this.position = position;
	        }
	         
	        public void run() 
	        {
	            AutoCompleteTextField.this.setText(AutoCompleteTextField.this.getText()+completion);
	            AutoCompleteTextField.this.setCaretPosition(position+completion.length());
	            AutoCompleteTextField.this.moveCaretPosition(position);
	            mode = Mode.COMPLETION;
	        }
	 }
	     
	private class CommitAction extends AbstractAction 
	{
	    public void actionPerformed(ActionEvent ev) 
	    {
	        if (mode == Mode.COMPLETION) 
	        {
	            int pos = AutoCompleteTextField.this.getSelectionEnd();
	           // AutoCompleteTextField.this.setText(AutoCompleteTextField.this.getText()+" ");
                
	            AutoCompleteTextField.this.setCaretPosition(pos);
	            mode = Mode.INSERT;
	        } 
	        else 
	        {
	        	AutoCompleteTextField.this.replaceSelection("\n");
	        }
	    }
	}
}
