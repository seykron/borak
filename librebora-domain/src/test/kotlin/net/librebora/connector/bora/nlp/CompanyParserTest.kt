package net.librebora.connector.bora.nlp

import net.librebora.support.nlp.PredictionService
import net.librebora.support.ObjectMapperFactory
import net.librebora.support.nlp.ClassifierConfig
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CompanyParserTest {

    val texts: List<String> = listOf(
        "<div><p><span xml:lang=\"en-US\" ><p><span>GRUPO COPAL S.A.</span></p> </span></p><p></p><p><span xml:lang=\"en-US\" ></span></p><p>Constitución. Escritura 302. Folio 783. 26/12/2018. Registro 1822 CABA. Adscripta. Socios. Liliana Silvia OSTAPCZUK, 27/10/1965, DNI 17922631, CUIT 27-17922631-1, contadora; y Jorge Alberto OSTAPCZUK, 2/1/1973, DNI 22931975, CUIT 20-22931975-3, empresario; argentinos, solteros, y domiciliados en Neuquén 1436, El Palomar, Morón, Pcia Bs As. Denominación. GRUPO COPAL S.A. Duración. 99 años. Objeto. FINANCIERA: Mediante aportes e inversiones de capitales a particulares, empresas o sociedades, para negocios realizados o a realizarse, constitución y transferencia de préstamos con o sin hipotecas y demás derechos reales, compraventa de títulos y acciones y otros valores mobiliarios y otorgamiento de créditos en general, ya sea en forma de prenda o cualquiera otra permitida por la ley, con excepción de las operaciones comprendidas en las leyes de entidades financieras y toda otra por la que se requiera el concurso público. Toda actividad que así lo requiera será realizada por profesionales con título habilitante en la materia. MANDATARIA: Mediante la realización de toda clase de operaciones sobre representaciones, consignaciones, comisiones y mandatos; y administración de bienes y capitales. Toda actividad que así lo requiera será realizada por profesionales con título habilitante en la materia. A tales fines, la sociedad tiene plena capacidad jurídica para adquirir derechos y contraer obligaciones, pudiendo realizar todo tipo de actos y contratos no prohibidos por las leyes o por este Estatuto. Capital. $ 100.000, 100.000 acciones valor nominal $ 1, 1 voto cada una. Administración. Directorio, de 1 a 5 miembros; igual o menor número de suplentes; por 3 ejercicios, reelegibles. Representación y uso de la firma social. Presidente o Vicepresidente, en su caso. Fiscalización. Prescinde. Ejercicio Social. 31/08. Directorio. Presidente: Liliana Silvia OSTAPCZUK; Director Suplente: Jorge Alberto OSTAPCZUK; domicilio especial en la sede social. Sede Social. Av Rivadavia 1545, piso 14°, departamento H, CABA. Autorizado según instrumento público Esc. Nº 302 de fecha 26/12/2018 Reg. Nº 1822 Alicia Telma Dopacio - Matrícula: 4465 C.E.C.B.A.</p><p>e. 02/01/2019 N° 99780/18 v. 02/01/2019</p></div>",
        "<div><p><span xml:lang=\"en-US\" ><p><span>GRUPO MC ARRI S.A.</span></p> </span></p><p></p><p><span xml:lang=\"en-US\" ></span></p><p>1) 19/12/18; 2) Marciano Arriola Martínez, 6/3/58, DNI92973789, Ibarro la 7257 Departamento 1, Caba; Elena Beatriz Figueredo Giubi, 21/8/75, DNI94833613, 9 de Setiembre 4405, Lanus, Pcia.Bs.As; ambos paragua yos, solteros, comerciantes; 3) GRUPO MC ARRI S.A.; 4) 99 años; 5) construcción de inmuebles urbanos y/o rurales, obras civiles, obras viales, desagües, loteos, servicios de reparación, refacción y mantenimiento de inmuebles, administración y explotación de inmuebles propios, de terceros y asociada a terceros; 6) $ 100.000,- acciones $ 1,- cada una; 7) 30/11; 8) Marciano Arriola Martínez 90%; Elena Beatriz Figueredo Giubi 10%; 9) Avda. Corrientes 1670, Piso 12º Departamento B, Caba; 10) Presidente: Marciano Arriola Martínez; Directora Suplen te: Elena Beatriz Figueredo Giubi; ambos domicilio especial: Avda. Corrientes 1670, Piso 12º Departamento B, Caba; Autorizado según instrumento público Esc. Nº 677 de fecha 19/12/2018 Reg. Nº 1919 Rafael SALAVE - T°: 114 F°: 344 C.P.A.C.F.</p><p>e. 02/01/2019 N° 99821/18 v. 02/01/2019</p></div>",
        "<div><p><span xml:lang=\"en-US\" ><p><span>HSVG S.A.</span></p> </span></p><p></p><p><span xml:lang=\"en-US\" ></span></p><p>Gerardo Daniel HADAD argentino 57 años DNI 14866551 empresario casado domicilio real especial Libertador General San Martin 4444 piso 43 CABA Fernando Rubén SOKOLOWICZ argentino 69 años DNI 8209160 empresario divorciado domicilio real especial Ángel Carranza 2180 CABA Benjamín Jorge VIJNOVSKY argentino 69 años DNI 7657337 divorciado domicilio especial real Santa Fe 911 piso 6 letra A CABA Carlos Alberto GOROSITO argentino 62 años DNI 12698423 empresario casado domicilio real especial Cavia 3033 piso 4 CABA 2) 21/12/2018 HSVG SA 4) Ángel Carranza 2180 CABA 5) organización promoción producción comercialización de espectáculos artísticos cinematográficos informativo publicitario documentales técnicos o científicos ya sea musical televisivo cinematográfico producción realización procesamiento distribución importación exportación de películas programas de televisión video tapes comisiones representaciones artísticas comerciales eventos artísticos producción realización procesamiento de películas videos Explotación comercialización de salas teatrales cinematográficas auditorios espacios en medios de comunicación Producción distribución importación exportación de series en formato digital 6) 99año desde IGJ 7) $ 100000 8) 1 a5 Titulares por 3 ejercicios 9) Presidente Gerardo Daniel HADAD Vice Presidente Benjamín Jorge VIJNOVSKY Vocal Carlos Alberto GOROSITO SUPLENTE Fernando Rubén SOKOLOWICZ 10) 30 setiembre Autorizado según instrumento público Esc. Nº 167 de fecha 21/12/2018 Reg. Nº 634 Eliana Judith Pollack de Rubinska - Matrícula: 5100 C.E.C.B.A.</p><p>e. 02/01/2019 N° 99762/18 v. 02/01/2019</p></div>",
        "<div><p><span xml:lang=\"en-US\" ><p><span>PARADIGMA S.P. S.A.</span></p> </span></p><p></p><p><span xml:lang=\"en-US\" ></span></p><p>1) Verónica Andrea CHOLIZ, argentina, nacida el 27/03/69, divorciada, calígrafo público nacional, DNI 20.775.195 y Macarena Azul FESTA, argentina, nacida el 22/12/98, soltera, empleada, DNI 41.565.399, ambas domiciliadas en Gabriela Mistral 2850, piso 1º dpto. A, CABA. 2) Esc. Púb. N° 609 del 26/12/2018 Registro 1774 CABA. 3) PARADIGMA S.P. S.A. 4) Gabriela Mistral 2850, piso 1º dpto. A, CABA. 5) 1) En jurisdicción de la Ciudad Autónoma de Buenos Aires, realizar investigaciones privadas, exclusivamente en los ámbitos civil, comercial y laboral y efectuar vigilancia o custodia de lugares o bienes dentro de inmuebles, todo debidamente ajustado al cumplimiento de la Ley 118 del Gobierno Autónomo de la Ciudad de Buenos Aires, sus modificaciones o las que pudieran suplantarlos en el futuro. Podrá hacerlo en tal carácter en todo el territorio de la República Argentina o en el extranjero, conforme a las leyes que regulan su funcionamiento en las distintas jurisdicciones. No realizará las comprendidas en la Ley 21526 o en cualquier otra que se dicte en lo sucesivo en su reemplazo o requiera la intermediación en el ahorro público. 2) En la Provincia de Buenos Aires, exclusivamente, como Agencia de Seguridad Privada, de acuerdo a lo establecido en el artículo segundo de la Ley Provincial 12297 o las que en su consecuencia se dicten, es decir: a) Vigilancia y protección de bienes; b) Escolta y protección de personas; c) Transporte, custodia y protección de cualquier objeto de tránsito lícito, a excepción de transporte de caudales; d) Vigilancia y protección de personas y bienes en espectáculos públicos, locales bailables y otros eventos o reuniones análogas; e) Obtención de evidencias en cuestiones civiles o para incriminar o desincriminar a una persona siempre que exista una persecución penal en el ámbito de la justicia por la comisión de un delito y tales servicios sean contratados en virtud de interés legítimo en el proceso penal. 6) 99 años 7) $ 100.000. 8) y 9) Directorio de 1 a 5 titulares por 3 ejercicios. Presidente del Directorio o al Vicepresidente, en su caso. Se prescinde de Sindicatura. Presidente: Verónica Andrea CHOLIZ y Directora Suplente: Macarena Azul FESTA. Ambas constituyeron domicilio especial en la sede social. 10) 31/12. Autorizado según instrumento público Esc. Nº 609 de fecha 26/12/2018 Reg. Nº 1774, CABA Jorge Eduardo Carullo - T°: 26 F°: 996 C.P.A.C.F.</p><p>e. 02/01/2019 N° 100070/18 v. 02/01/2019</p></div>"
    )
    private val logger: Logger = LoggerFactory.getLogger(CompanyParserTest::class.java)
    private val predictionService: PredictionService =
        PredictionService(
            classifiersConfig = listOf(
                ClassifierConfig(
                    category = "jobs",
                    resourceLocation = "./db/jobs.txt"
                )
            )
        )
    private val sectionTagger: SectionTagger =
        SectionTagger(predictionService)

    @Test
    fun parse() {
        val wordTokenizer = WordTokenizer()
        val parser = CompanyParser(
            classifiedDocumentTokenizer = ClassifiedDocumentTokenizer(
                wordTokenizer = wordTokenizer
            ),
            indexedDocumentTokenizer = IndexedDocumentTokenizer(),
            sectionTagger = sectionTagger,
            partnersParser = PartnersParser(),
            fileInfoParser = FileInfoParser()
        )
        val companies = texts.map { text ->
            parser.parse("file-id", text)
        }

        println(ObjectMapperFactory.snakeCaseMapper.writeValueAsString(companies))
        assert(companies.isNotEmpty())
    }
}