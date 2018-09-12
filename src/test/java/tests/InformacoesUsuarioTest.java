package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        // abrindo o navegador
        System.setProperty ("webdriver.chrome.driver", "E:\\Maicon Geral\\WebDrivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Navegando para a pagina do AutomationPractice
        navegador.get("http://www.automationpractice.com.");

    }

    @Test
    public void testAddInfoUser () {

        // Clicar no produto de nome "Blouse"
        navegador.findElement(By.linkText("Blouse")).click();

        // Clicar no botao "Add to cart"
        navegador.findElement(By.id("add_to_cart")).click();

        // Clicar no botao com o texto "Proceed to checkout"
        navegador.findElement(By.linkText("Proceed to checkout")).click();

        // Verificar se o carrinho está correto
        WebElement BlouseBlack = navegador.findElement(By.xpath("//tr[@id=\"product_2_7_0_0\"]//td[@class=\"cart_description\"]//p[@class=\"product-name\"]"));
        String mensagemBlack = BlouseBlack.getText();
        assertEquals("Blouse", mensagemBlack);

        // Continuar para o Check Out
        navegador.findElement(By.linkText("Proceed to checkout")).click();

        // Clicar no campo email
        navegador.findElement(By.id("email_create")).click();

        // Inserir o email
        navegador.findElement(By.id("email_create")).sendKeys("maiconfiguerosviski@gmail.com");

        // Clicar no Botao Create an Account
        navegador.findElement(By.id("SubmitCreate")).click();

        // Escolher genero
        navegador.findElement(By.id("id_gender1")).click();

        // Inserir um nome
        navegador.findElement(By.id("customer_firstname")).sendKeys("Maicon");

        //Inserir um sobrenome
        navegador.findElement(By.id("customer_lastname")).sendKeys("Figueredo");

        // Inserir senha
        navegador.findElement(By.id("passwd")).sendKeys("123456");

        // Colocar endereço
        navegador.findElement(By.id("address1")).sendKeys("BroadWay");

        // Adicionar cidade
        navegador.findElement(By.id("city")).sendKeys("New York");

        // Selecionar o estado
        WebElement mensagemPop = navegador.findElement(By.id("id_state"));
        new Select(mensagemPop).selectByVisibleText("Ohio");

        // Colocar Postal Code
        navegador.findElement(By.id("postcode")).sendKeys("00000");

        // Colocar numero de telefone
        navegador.findElement(By.id("phone_mobile")).sendKeys("87654321");

        // Informar endereço alternativo
        navegador.findElement(By.id("alias")).sendKeys("Highway");

        // Clicar no botão Register
        navegador.findElement(By.id("submitAccount")).click();

        // Valide se o endereço está correto e prossiga.
        WebElement Billing = navegador.findElement(By.linkText("Maicon Figueredo"));
        String mensagem = Billing.getText();
        assertEquals("Maicon Figueredo", mensagem);

        // Prossiga clicando no botao Proceed to checkout
        navegador.findElement(By.name("processAddress")).click();

        // Aceite os termos de serviço e prossiga.
        navegador.findElement(By.id("cgv")).click();
        navegador.findElement(By.name("processCarrier")).click();

        // Valide o valor total da compra.
        WebElement TotalPrice = navegador.findElement(By.id("total_price"));
        String mensagemPrice = TotalPrice.getText();
        assertEquals("$29.00", mensagemPrice);

        //  Selecione um método de pagamento e prossiga.
        navegador.findElement(By.xpath("//div[@id=\"HOOK_PAYMENT\"]//a[@title=\"Pay by bank wire\"]")).click();

        // Confirme a compra e prossiga... Clicar em um link que tem o texto "I confirm my order"
        navegador.findElement(By.xpath(" //div[@id=\"center_column\"]//button[@type=\"submit\"]")).click();

        // Valide se a compra foi finalizada com sucesso.
        WebElement Payment = navegador.findElement(By.xpath("//div[@id=\"center_column\"]//strong[@class=\"dark\"]"));
        String mensagemPayment = Payment.getText();
        assertEquals("Your order on My Store is complete.", mensagemPayment);

    }

    @After
    public void tearDown(){
        // Fechar o navegador
        navegador.quit();
        }
}
