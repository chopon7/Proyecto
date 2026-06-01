package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Espacio;
import models.Vehiculo;
import repository.EspacioRepository;

public class EstacionamientoView extends JFrame {

	// Atributos
	private Espacio[] espacios;
	private JPanel[] panelesCeldas;
	private JLabel[] lblNumeros;
	private JLabel[] lblEstados;

	private JLabel lblContadorLibres;
	private JLabel lblContadorOcupados;

	private final int TOTAL_ESPACIOS = 30;

	private final Color COLOR_LIBRE = new Color(39, 174, 96); // Verde esmeralda
	private final Color COLOR_OCUPADO = new Color(211, 47, 47); // Rojo carmin
	private final Color COLOR_FONDO_APP = new Color(240, 242, 245); // Gris claro
	private final Color COLOR_TEXTO = Color.WHITE;

	private final Font FUENTE_NUMERO = new Font("Segoe UI", Font.BOLD, 22);
	private final Font FUENTE_ESTADO = new Font("Segoe UI", Font.BOLD, 12);
	private final Font FUENTE_DASHBOARD = new Font("Segoe UI", Font.BOLD, 16);

	// Constructores
	public EstacionamientoView() {
		setSize(1100, 650);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Estacionamiento");
		setLocationRelativeTo(null);
		getContentPane().setBackground(COLOR_FONDO_APP);
		setLayout(new BorderLayout());

		inicializarComponentes();
		setVisible(true);
	}

	// Metodos
	public void inicializarComponentes() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});

		JPanel panelResumen = new JPanel(new GridLayout(1, 3, 20, 0));
		panelResumen.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 25));
		panelResumen.setOpaque(false);

		JLabel lblTotal = new JLabel("Espacios Totales: " + TOTAL_ESPACIOS, SwingConstants.CENTER);
		lblTotal.setFont(FUENTE_DASHBOARD);

		lblContadorLibres = new JLabel("Disponibles: 30", SwingConstants.CENTER);
		lblContadorLibres.setFont(FUENTE_DASHBOARD);
		lblContadorLibres.setForeground(COLOR_LIBRE);

		lblContadorOcupados = new JLabel("Ocupados: 0", SwingConstants.CENTER);
		lblContadorOcupados.setFont(FUENTE_DASHBOARD);
		lblContadorOcupados.setForeground(COLOR_OCUPADO);

		panelResumen.add(lblTotal);
		panelResumen.add(lblContadorLibres);
		panelResumen.add(lblContadorOcupados);
		add(panelResumen, BorderLayout.NORTH);

		JPanel contenedorGrid = new JPanel(new GridLayout(5, 6, 15, 15));
		contenedorGrid.setBorder(BorderFactory.createEmptyBorder(15, 25, 25, 25));
		contenedorGrid.setOpaque(false);

		espacios = new Espacio[TOTAL_ESPACIOS];
		panelesCeldas = new JPanel[TOTAL_ESPACIOS];
		lblNumeros = new JLabel[TOTAL_ESPACIOS];
		lblEstados = new JLabel[TOTAL_ESPACIOS];

		EspacioRepository espacioRepository = new EspacioRepository();
		List<Integer> cajonesLibres = espacioRepository.getNumerosCajonesLibres();

		for (int i = 0; i < TOTAL_ESPACIOS; i++) {
			int numeroCajon = i + 1;
			espacios[i] = new Espacio(numeroCajon);

			if (cajonesLibres != null && !cajonesLibres.contains(numeroCajon)) {
				Vehiculo vehiculoBD = new Vehiculo("", "", "", "", "");
				espacios[i].registrarEntrada(-1, vehiculoBD);
			}

			// Tarjetas visuales
			panelesCeldas[i] = new JPanel(new GridLayout(2, 1, 0, 5));
			panelesCeldas[i].setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
			panelesCeldas[i].setBackground(COLOR_LIBRE);

			// Etiqueta para el numero
			lblNumeros[i] = new JLabel(String.valueOf(espacios[i].getNumero()), SwingConstants.CENTER);
			lblNumeros[i].setFont(FUENTE_NUMERO);
			lblNumeros[i].setForeground(COLOR_TEXTO);

			// Etiqueta para el estado
			lblEstados[i] = new JLabel("DISPONIBLE", SwingConstants.CENTER);
			lblEstados[i].setFont(FUENTE_ESTADO);
			lblEstados[i].setForeground(COLOR_TEXTO);

			panelesCeldas[i].add(lblNumeros[i]);
			panelesCeldas[i].add(lblEstados[i]);

			contenedorGrid.add(panelesCeldas[i]);
			refrescarCelda(i);
		}

		add(contenedorGrid, BorderLayout.CENTER);

	}

	public void refrescarCelda(int indice) {
		if (indice >= 0 && indice < TOTAL_ESPACIOS) {
			Espacio esp = espacios[indice];

			if (esp.isOcupado() && esp.getMiVehiculo() != null) {
				panelesCeldas[indice].setBackground(COLOR_OCUPADO);
				lblEstados[indice].setText("OCUPADO");

			} else {
				panelesCeldas[indice].setBackground(COLOR_LIBRE);
				lblEstados[indice].setText("DISPONIBLE");
			}

			actualizarContadoresGlobales();
		}
	}

	private void actualizarContadoresGlobales() {
		int ocupados = 0;
		for (Espacio esp : espacios) {
			if (esp != null && esp.isOcupado()) {
				ocupados++;
			}
		}

		int libres = TOTAL_ESPACIOS - ocupados;

		if (lblContadorLibres != null && lblContadorOcupados != null) {
			lblContadorLibres.setText("Disponibles: " + libres);
			lblContadorOcupados.setText("Ocupados: " + ocupados);
		}
	}

	public void ocuparEspacio(int indice, int idVehiculo, Vehiculo miVehiculo) {
		if (indice >= 0 && indice < TOTAL_ESPACIOS) {
			espacios[indice].registrarEntrada(idVehiculo, miVehiculo);
			refrescarCelda(indice);
		}
	}

	public void liberarEspacio(int indice) {
		if (indice >= 0 && indice < TOTAL_ESPACIOS) {
			espacios[indice].registrarSalida();
			refrescarCelda(indice);
		}
	}

	private void handleClose() {
		int opcion = JOptionPane.showConfirmDialog(this,
				"Seguro que desea cerrar la ventana? Se perderan los datos del formulario");

		if (opcion == JOptionPane.YES_OPTION) {
			this.dispose();
		}

	}

}
