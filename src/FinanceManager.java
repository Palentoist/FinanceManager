import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinanceManager extends JFrame 
{

    private JTextField amountField, categoryField, descriptionField;
    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JLabel incomeLabel, expenseLabel, balanceLabel;
    private double totalIncome = 0, totalExpense = 0;

    public FinanceManager() 
    {
        
        setTitle("Personal Finance Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        
        incomeLabel = new JLabel("Total Income: $0.0");
        expenseLabel = new JLabel("Total Expense: $0.0");
        balanceLabel = new JLabel("Balance: $0.0");
        
        topPanel.add(incomeLabel);
        topPanel.add(expenseLabel);
        topPanel.add(balanceLabel);
        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Type", "Amount", "Category", "Description"}, 0);
        transactionTable = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JPanel inputPanel = new JPanel(new GridLayout(1, 4));
        
        amountField = new JTextField();
        categoryField = new JTextField();
        descriptionField = new JTextField();
        
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        bottomPanel.add(inputPanel);

        JPanel buttonPanel = new JPanel();
        JButton addIncomeButton = new JButton("Add Income");
        JButton addExpenseButton = new JButton("Add Expense");
        
        buttonPanel.add(addIncomeButton);
        buttonPanel.add(addExpenseButton);
        bottomPanel.add(buttonPanel);
        add(bottomPanel, BorderLayout.SOUTH);

        addIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTransaction("Income");
            }
        });

        addExpenseButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                addTransaction("Expense");
            }
        });

        setVisible(true);
    }

    private void addTransaction(String type) 
    {
        try 
        {
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            String description = descriptionField.getText();

            if (type.equals("Income")) 
            {
                totalIncome += amount;
            } else 
            {
                totalExpense += amount;
            }

            double balance = totalIncome - totalExpense;
            
            incomeLabel.setText("Total Income: $" + totalIncome);
            expenseLabel.setText("Total Expense: $" + totalExpense);
            balanceLabel.setText("Balance: $" + balance);

            tableModel.addRow(new Object[]{type, amount, category, description});

            amountField.setText("");
            categoryField.setText("");
            descriptionField.setText("");
        } 
        catch (NumberFormatException ex) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}