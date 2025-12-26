package tests;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.*;
import pageObjects.indent.*;
import testComponents.BaseTest;

public class EndToEndFlowTest extends BaseTest {

	@Test(dataProvider = "getIndentData")
	public void completeIndentFlow(HashMap<String, String> data) throws InterruptedException {

		//depot
		landingPage.openMDMLanding();
		DepotPage depotPage = landingPage.openDepotPage();
		depotPage.createDepot(data.get("depotName"),data.get("depotRefId"));
		Assert.assertEquals(depotPage.getDepotSuccessMessage(), "Depot added successfully");
		depotPage.searchAndOpenDepot(data.get("depotRefId"));
		depotPage.addGate(data.get("gateName"), data.get("gateRefId"), data.get("gateAddress"), data.get("gateCity"),
				data.get("gateState"), data.get("gatePinCode"), data.get("gateUser"));
		depotPage.navigateBackToDepot();
		depotPage.closeAndReturnToParent();

		//consignee
		ConsigneePage consigneePage = landingPage.openConsigneePage();
		consigneePage.createConsignee(data.get("consigneeName"), data.get("consigneeRef"), data.get("consigneeState"),
				data.get("consigneeRegion"), data.get("consigneeCity"), data.get("consigneePin"),
				data.get("consigneeMobile"));
		consigneePage.navigateBackToCategory();

		//transporter
		TransporterPage transporterPage = landingPage.openTransporterPage();
		transporterPage.createTransporter(data.get("transporterName"), data.get("transporterRef"), data.get("gst"));
		transporterPage.addManager(data.get("managerName"), data.get("managerMobile"), data.get("managerEmail"));
		transporterPage.navigateBackToCategory();

		//material
		MaterialsPage materialsPage = landingPage.openMaterialsPage();
		materialsPage.createMaterial(data.get("materialName"), data.get("materialCode"));

		//vehicle
		VehiclePage vehiclePage = landingPage.openVehiclePage();
		vehiclePage.createVehicle(data.get("vehicleName"), data.get("vehicleCft"), data.get("vehicleKg"));

		//indent
		IndentLandingPage indentLanding = landingPage.openIndentLandingPage();
		String indentId = indentLanding.clickAddIndent().selectSourceDepot(data.get("sourceDepot"))
				.selectConsignee(data.get("consigneeRef"))
				.selectVehicle(data.get("vehicleType"), data.get("indentRate"))
				.createIndent(data.get("transporterSearch"));
		Assert.assertTrue(indentId.contains("INDENT"));

		//assign truck
		IndentAssignTruckPage assignTruckPage = new IndentAssignTruckPage(driver);
		assignTruckPage.filterIndent(data.get("filterValue"));
		assignTruckPage.searchIndentById(indentId);
		assignTruckPage.assignTruck(data.get("truckPrefix"), data.get("truckNumber"), data.get("driverMobile"),
				data.get("driverName"), data.get("truckLength"), data.get("truckWidth"), data.get("truckHeight"));
		assignTruckPage.markTruckReported();
		assignTruckPage.markTruckIn();
		assignTruckPage.downloadDocuments();
	}

	@DataProvider
	public Object[][] getIndentData() throws Exception {
 
	    List<HashMap<String,String>> datas = getJsonData(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.json");
		Object[][] inputs = {{datas.get(0)}};
		return inputs;
		
	}

	
}
