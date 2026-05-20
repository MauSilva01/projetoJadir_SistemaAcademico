package sistemaacademico.view;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane;

	private PainelDadosPessoais painelDadosPessoais;
	private PainelCurso painelCurso;
	private PainelNotasFaltas painelNotasFaltas;
	private PainelBoletim painelBoletim;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		});
	}

	public TelaPrincipal() {

		setTitle("Sistema Acadêmico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		criarPaineis();
		criarMenu();
		criarAbas();
	}

	private void criarPaineis() {

		painelDadosPessoais = new PainelDadosPessoais();
		painelCurso = new PainelCurso();
		painelNotasFaltas = new PainelNotasFaltas();
		painelBoletim = new PainelBoletim();
	}

	private void criarMenu() {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 625, 25);

		getContentPane().add(menuBar);

		/*
		 * MENU ALUNO
		 */

		JMenu menuAluno = new JMenu("Aluno");
		menuBar.add(menuAluno);

		JMenuItem itemSalvarAluno = new JMenuItem("Salvar");

		itemSalvarAluno.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_S,
						KeyEvent.CTRL_DOWN_MASK
				));

		itemSalvarAluno.addActionListener(
				e -> painelDadosPessoais.salvarAluno());

		menuAluno.add(itemSalvarAluno);

		JMenuItem itemAlterarAluno = new JMenuItem("Alterar");

		itemAlterarAluno.addActionListener(
				e -> painelDadosPessoais.alterarAluno());

		menuAluno.add(itemAlterarAluno);

		JMenuItem itemConsultarAluno = new JMenuItem("Consultar");

		itemConsultarAluno.addActionListener(
				e -> painelDadosPessoais.consultarAluno());

		menuAluno.add(itemConsultarAluno);

		JMenuItem itemExcluirAluno = new JMenuItem("Excluir");

		itemExcluirAluno.addActionListener(
				e -> painelDadosPessoais.excluirAluno());

		menuAluno.add(itemExcluirAluno);

		JMenuItem itemSair = new JMenuItem("Sair");

		itemSair.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_R,
						KeyEvent.SHIFT_DOWN_MASK
				));

		itemSair.addActionListener(e -> System.exit(0));

		menuAluno.add(itemSair);

		/*
		 * MENU CURSO
		 */

		JMenu menuCurso = new JMenu("Curso");
		menuBar.add(menuCurso);

		JMenuItem itemSalvarCurso = new JMenuItem("Salvar");
		itemSalvarCurso.addActionListener(
				e -> painelCurso.salvarCurso());
		menuCurso.add(itemSalvarCurso);

		JMenuItem itemConsultarCurso = new JMenuItem("Consultar");
		itemConsultarCurso.addActionListener(
				e -> painelCurso.consultarCurso());
		menuCurso.add(itemConsultarCurso);

		JMenuItem itemAlterarCurso = new JMenuItem("Alterar");
		itemAlterarCurso.addActionListener(
				e -> painelCurso.alterarCurso());
		menuCurso.add(itemAlterarCurso);

		JMenuItem itemExcluirCurso = new JMenuItem("Excluir");
		itemExcluirCurso.addActionListener(
				e -> painelCurso.excluirCurso());
		menuCurso.add(itemExcluirCurso);

		/*
		 * MENU NOTAS E FALTAS
		 */

		JMenu menuNotasFaltas = new JMenu("Notas e Faltas");
		menuBar.add(menuNotasFaltas);

		JMenuItem itemSalvarNota = new JMenuItem("Salvar");

		itemSalvarNota.addActionListener(
				e -> painelNotasFaltas.salvarNotaFalta());

		menuNotasFaltas.add(itemSalvarNota);

		JMenuItem itemAlterarNota = new JMenuItem("Alterar");

		itemAlterarNota.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_A,
						KeyEvent.CTRL_DOWN_MASK
				));

		itemAlterarNota.addActionListener(
				e -> painelNotasFaltas.alterarNotaFalta());

		menuNotasFaltas.add(itemAlterarNota);

		JMenuItem itemExcluirNota = new JMenuItem("Excluir");

		itemExcluirNota.addActionListener(
				e -> painelNotasFaltas.excluirNotaFalta());

		menuNotasFaltas.add(itemExcluirNota);

		JMenuItem itemConsultarNota = new JMenuItem("Consultar");

		itemConsultarNota.addActionListener(
				e -> painelNotasFaltas.consultarNotas());

		menuNotasFaltas.add(itemConsultarNota);

		/*
		 * MENU AJUDA
		 */

		JMenu menuAjuda = new JMenu("Ajuda");
		menuBar.add(menuAjuda);

		JMenuItem itemSobre = new JMenuItem("Sobre");

		itemSobre.addActionListener(e -> {

			javax.swing.JOptionPane.showMessageDialog(
					null,
					"Sistema Acadêmico\n\n"
					+ "Projeto desenvolvido em Java + MySQL\n"
					+ "Utilizando Swing, DAO e MVC."
			);
		});

		menuAjuda.add(itemSobre);
	}

	private void criarAbas() {

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(7, 45, 602, 365);

		getContentPane().add(tabbedPane);

		tabbedPane.addTab(
				"Dados Pessoais",
				null,
				painelDadosPessoais,
				null
		);

		tabbedPane.addTab(
				"Curso",
				null,
				painelCurso,
				null
		);

		tabbedPane.addTab(
				"Notas e Faltas",
				null,
				painelNotasFaltas,
				null
		);

		tabbedPane.addTab(
				"Boletim",
				null,
				painelBoletim,
				null
		);
	}
}