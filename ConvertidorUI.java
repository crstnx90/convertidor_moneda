import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ConvertidorUI extends JFrame implements ActionListener{
    private JComboBox<String> opcion1,opcion2;
    private JTextField textFieldMonto;
    private JButton botonConvertir,botonBorrar,botonSalir;
    private JLabel labelBienvenida, labelResultado;

    //Siglas de las divisas
    public static final String[] divisas={"ARS-Argentine Peso","AUD-Australian Dollar","BOB-Bolivian Peso","BRL-Brazilian Real","CAD-Canadian Dollar","CDF-Congolese Franc", "CHF-Swiss Franc","CLP-Chilean Peso","CNY-Chinese Renminbi","COP-Colombian Peso","CRC-Costa RIcan Colon","EUR-Euro","JPY-Japanese Yen","KRW-South Korean Won","MAD-Moroccan Dirham","MXN-Mexican Peso","PEN-Peruvian Sol","RUB-Russian Ruble","TWD-New Taiwan Dollar","USD-United States Dollar","ZAR-South African Rand"};

    public ConvertidorUI(){
        super("Cristian's Exchange Converter V. 1.0 for Alura Challenges");
        setSize(650,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //Crear paneles organizado de los componentes
        JPanel panelPpal=new JPanel(new GridBagLayout());
        JPanel panelBotones=new JPanel(new FlowLayout());

        //Crear componentes
        opcion1= new JComboBox<>(divisas);
        opcion2=new JComboBox<>(divisas);
        textFieldMonto= new JTextField(10);
        botonConvertir=new JButton("Convertir");
        botonBorrar=new JButton("Borrar");
        botonSalir=new JButton("Salir");
        labelBienvenida= new JLabel("Bienvenido a nuestro sistema de conversión de divisas");
        labelResultado=new JLabel("");

        //Agregar componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPpal.add(labelBienvenida, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panelPpal.add(new JLabel("De:"), gbc);
        gbc.gridx = 1;
        panelPpal.add(opcion1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPpal.add(new JLabel("A:"), gbc);
        gbc.gridx = 1;
        panelPpal.add(opcion2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPpal.add(new JLabel("Monto:"), gbc);
        gbc.gridx = 1;
        panelPpal.add(textFieldMonto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelPpal.add(new JLabel("Resultado:"), gbc);
        gbc.gridx = 1;
        panelPpal.add(labelResultado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panelPpal.add(panelBotones, gbc);

        //Agregando botones
        panelBotones.add(botonConvertir);
        panelBotones.add(botonBorrar);
        panelBotones.add(botonSalir);

        //AGregando acciones
        botonConvertir.addActionListener(this);
        botonBorrar.addActionListener(this);
        botonSalir.addActionListener(this);

        //Agregando panel ppal al JFRAME
        add(panelPpal);
        setVisible(true);

    }

    //Eventos de boton
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==botonConvertir) {
            try {
                //Obtiene el indice de las divisas
                String divisaOrigen=((String)opcion1.getSelectedItem()).substring(0,3);
                String divisaTarget=((String)opcion2.getSelectedItem()).substring(0,3);
                
                //Verifica si las divisas son iguales
                if(divisaOrigen.equals(divisaTarget)){
                    labelResultado.setText("Seleccione divisas diferentes");
                    return;//Sale del actionPerformed
                }

                //Obtiene monto para convertir
                double monto=Double.parseDouble(textFieldMonto.getText());
                
                //Realizar la conversión
                ConvertirMoneda convertirMoneda=new ConvertirMoneda();
                Moneda resultado=convertirMoneda.convierteMoneda(divisaOrigen, divisaTarget, monto);

                //Muestra resultado en el panel
                labelResultado.setText(resultado.toString());
            } catch (NumberFormatException ex) {
                labelResultado.setText("Ingrese un número válido");
            } catch (Exception ex){
                labelResultado.setText("Error en la conversión, verifique conexión");
            }
        } else if (e.getSource()==botonBorrar) {
            textFieldMonto.setText("");
            labelResultado.setText("");
        } else if (e.getSource()==botonSalir) {
            System.exit(0);
        }
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new ConvertidorUI());
    }

}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
