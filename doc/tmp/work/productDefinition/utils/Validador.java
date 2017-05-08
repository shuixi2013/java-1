package work.productDefinition.utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Validador {
	private String erro;

	private void setErro(String erro) {
		this.erro = erro;
	}

	public String getErro() {
		return erro;
	}

	public Validador() {
		erro = "";
	}

	public void Valida(JTextField txt, int maximo) {
		if (txt.getText().length() > maximo) {
			JOptionPane.showMessageDialog(null, "Erro: M谩ximo de " + maximo
					+ " caracteres !", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void Valida(JTextField txt, String tipo) {
		if (tipo.equals("int")) {
			if (!txt.getText().isEmpty()) {
				try {
					Integer.parseInt(txt.getText());
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Erro: Digite apenas n煤meros inteiros!\n", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if (tipo.equals("double")) {
			if (!txt.getText().isEmpty()) {
				try {
					Double.parseDouble(txt.getText());
				} catch (Exception e) {
					javax.swing.JOptionPane
							.showMessageDialog(
									null,
									"Erro: Digite apenas n煤meros!\n"
											+ "Utilize um ponto para separar os valores ao inv茅s de v铆rgula !",
									"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public boolean Valida(JFrame frame) {
		List<JPanel> panel = new ArrayList<JPanel>();
		panel.add((JPanel) frame.getContentPane());
		return Valida(panel);
	}

	public boolean Valida(JInternalFrame frame) {
		List<JPanel> panel = new ArrayList<JPanel>();
		JPanel pnl = (JPanel) frame.getLayeredPane().getComponent(0);
		// Percorre todos os Components do JPanel "pai"
		for (Component comp : pnl.getComponents()) {
			if (comp instanceof JTabbedPane) {
				JTabbedPane tabbed = (JTabbedPane) comp;
				for (Component c : tabbed.getComponents()) {
					panel.add((JPanel) c);
				}
				return Valida(panel);
			} else {
				if (panel.isEmpty())
					panel.add(pnl);
			}
		}
		return Valida(panel);
	}

	public boolean Valida(JInternalFrame frame, String nome) {
		List<JPanel> panel = new ArrayList<JPanel>();
		JPanel pnl = (JPanel) frame.getLayeredPane().getComponent(0);

		for (Component comp : pnl.getComponents()) {
			if (comp instanceof JTabbedPane) {
				JTabbedPane tabbed = (JTabbedPane) comp;
				for (Component c : tabbed.getComponents()) {
					if (c.getName().equals(nome))
						panel.add((JPanel) c);
				}
				return Valida(panel);
			} else {
				if (panel.isEmpty())
					panel.add(pnl);
			}
		}
		return Valida(panel);
	}

	private boolean Valida(List<JPanel> panel) {
		for (JPanel pnl : panel) {
			Valida(pnl);
		}
		if (getErro().isEmpty())
			return false;
		else
			return true;
	}

	private void Valida(JPanel panel) {
		for (Component comp : panel.getComponents()) {
			if (comp instanceof JPanel) {
				Valida((JPanel) comp);
			} else {
				if (comp instanceof JTextField
						&& !(comp instanceof JFormattedTextField)
						&& !(comp instanceof JTextArea)) {
					Valida((JTextField) comp);
				}
				if (comp instanceof JTextArea) {
					Valida((JTextArea) comp);
				}
				if (comp instanceof JFormattedTextField) {
					Valida((JFormattedTextField) comp);
				}
			}
		}
	}

	private void Valida(JTextField txt) {
		if (txt.getText().isEmpty()) {
			if (getErro().isEmpty()) {
				setErro("Erro - Preencha os campos abaixo:\n");
				setErro(getErro() + txt.getName() + "\n");
			} else {
				setErro(getErro() + txt.getName() + "\n");
			}
		}
	}

	private void Valida(JTextArea txt) {
		if (txt.getText().isEmpty()) {
			if (getErro().isEmpty()) {
				setErro("Erro - Preencha os campos abaixo:\n");
				setErro(getErro() + txt.getName() + "\n");
			} else {
				setErro(getErro() + txt.getName() + "\n");
			}
		}
	}

	private void Valida(JFormattedTextField txt) {
		if (txt.getText().substring(2, txt.getText().length())
				.replaceAll("\\s+", "").isEmpty()) {
			if (getErro().isEmpty()) {
				setErro("Erro - Preencha os campos abaixo:\n");
				setErro(getErro() + txt.getName() + "\n");
			} else {
				setErro(getErro() + txt.getName() + "\n");
			}
		}
	}
}
