package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Transactional
public class ExportService {
    private ClientService clientService;

    public ExportService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void clientsCSV(Writer writer) throws IOException {
        PrintWriter printWriter = new PrintWriter(writer);

        List<Client> allClients = clientService.findAllClients();
        LocalDate now = LocalDate.now();
        printWriter.println("Id" + ";" + "Nom" + ";" + "Prenom" + ";" + "Date de Naissance");

        for (Client client : allClients) {
            printWriter.println(client.getId() + ";"
                    + client.getNom() + ";"
                    + client.getPrenom() + ";"
                    + client.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        }
    }

    public void clientsXLSX(Workbook workbook, List<Client> allClients, HttpServletResponse response) throws IOException {
        Sheet sheet = workbook.createSheet("Clients");
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("Id");

        headerRow.createCell(1).setCellValue("Prénom");

        headerRow.createCell(2).setCellValue("Nom");

        int iRow = 1;
        for (Client client : allClients) {
            Row row = sheet.createRow(iRow);

            row.createCell(0).setCellValue(client.getId());

            row.createCell(1).setCellValue(client.getPrenom());

            row.createCell(2).setCellValue(client.getNom());

            iRow = iRow + 1;
        }
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    public void factureXLSXByClient(Workbook workbook, HttpServletResponse response, List<Facture> factures) throws IOException {
        Sheet sheet = workbook.createSheet("Facture");
        Row headerRow = sheet.createRow(0);

        Cell cellId = headerRow.createCell(0);
        cellId.setCellValue("Id");

        Cell cellTotal = headerRow.createCell(1);
        cellTotal.setCellValue("Prix Total");

        int iRow = 1;
        for (Facture facture : factures) {
            Row row = sheet.createRow(iRow);

            row.createCell(0).setCellValue(facture.getId());

            row.createCell(1).setCellValue(facture.getTotal());

            iRow = iRow + 1;
        }
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
