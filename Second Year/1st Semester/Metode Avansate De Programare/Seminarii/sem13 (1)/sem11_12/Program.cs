
using Sem11_12.Model;
using Sem11_12.Model.Validator;
using Sem11_12.Repository;
using Sem11_12.Service;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Security.Cryptography;

namespace Sem11_12
{
    class Program
    {
        static string RequestInput(string msg)
        {
            Console.Write(msg + " : ");
            return Console.ReadLine();
        }

        static void Main(string[] args)
        {
            int luna = Convert.ToInt32(RequestInput("Luna"));
            int an = Convert.ToInt32(RequestInput("An"));

            PrintHeader();
            Console.WriteLine();
            GetAngajatiLinq(luna, an).ForEach(PrintRow);

            Console.WriteLine(); 
            Console.WriteLine();

            PrintHeader();
            Console.WriteLine();
            GetAngajatiSql(luna, an).ForEach(PrintRow);

            var total = GetAngajatiSql(luna, an).Sum(kv => kv.Value);
            PrintRow(KeyValuePair.Create((Angajat)null, total));
        }      

        static void PrintHeader()
        {
            Console.WriteLine("Nume".PadRight(40) + " | " + "Nivel".PadLeft(20) + " | " + "Salariu".PadLeft(10));

        }

        static void PrintRow(KeyValuePair<Angajat,double> kv)
        {
            var a = kv.Key;
            var salar = kv.Value;
            if(a!=null)
                Console.WriteLine(a.Nume.PadRight(40) + " | " + a.Nivel.ToString().PadLeft(20) + " | " + salar.ToString().PadLeft(10));
            else
            {
                Console.WriteLine("".PadRight(40) + "   " + "Total".PadLeft(20) + " : " + salar.ToString().PadLeft(10));
            }
        }
  

        static List<KeyValuePair<Angajat,double>> GetAngajatiLinq(int month, int year)
        {
            var result = GetPontajService().FindAllPontaje()
                .Where(pontaj => pontaj.Date.Month == month && pontaj.Date.Year == year)
                .Select(p => new { AngajatId = p.Angajat.ID, Ore = p.Sarcina.NrOreEstimate })
                .GroupBy(x => x.AngajatId)
                .Select(grp =>
                    KeyValuePair.Create
                        (GetAngajatService().FindAllAngajati().Where(a => a.ID == grp.First().AngajatId)
                        .FirstOrDefault(),
                        grp.Sum(x => x.Ore)))
                .Select(x => KeyValuePair.Create(x.Key, x.Value * x.Key.VenitPeOra))                
                .ToList();

            result.Sort((kv1, kv2) => Math.Sign(kv1.Value - kv2.Value));
            result.Sort((kv1, kv2) => kv1.Key.Nivel - kv2.Key.Nivel);

            /*result.Sort((kv1, kv2) =>
            {
                if (kv1.Key.Nivel != kv2.Key.Nivel)
                   return kv1.Key.Nivel - kv2.Key.Nivel;
                return kv1.Value - kv2.Value < 0 ? -1 : 1;
            });*/

            return result;
        }

        static List<KeyValuePair<Angajat, double>> GetAngajatiSql(int month, int year)
        {
            var pontaje = GetPontajService().FindAllPontaje();
            var angajati = GetAngajatService().FindAllAngajati();
            var result = (from pontaj in pontaje
                          where pontaj.Date.Month==month && pontaj.Date.Year==year
                          group pontaj by pontaj.Angajat.ID into PontajePerAngajat
                          select KeyValuePair.Create(                             
                             (from angajat in angajati 
                              where angajat.ID == PontajePerAngajat.Key
                              select angajat).First(),
                             PontajePerAngajat.Sum(x => x.Sarcina.NrOreEstimate *
                                (from angajat in angajati
                                 where angajat.ID == PontajePerAngajat.Key
                                 select angajat).First().VenitPeOra))).ToList();

            result.Sort((kv1, kv2) => Math.Sign(kv1.Value - kv2.Value));
            result.Sort((kv1, kv2) => kv1.Key.Nivel - kv2.Key.Nivel);          

            return result;
        }


        private static AngajatService GetAngajatService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["angajatiFileName"];
            string fileName = "..\\..\\..\\data\\angajati.txt";
            IValidator<Angajat> vali = new AngajatValidator();

            IRepository<string, Angajat> repo1 = new AngajatInFileRepository(vali, fileName);
            AngajatService service = new AngajatService(repo1);
            return service;
        }

        private static SarcinaService GetSarcinaService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["sarciniFileName"];
            string fileName2 = "..\\..\\..\\data\\sarcini.txt";
            IValidator<Sarcina> vali = new SarcinaValidator();

            IRepository<string, Sarcina> repo1 = new SarcinaInFileRepository(vali, fileName2);
            SarcinaService service = new SarcinaService(repo1);
            return service;
        }

        private static PontajService GetPontajService()
        {
            //string fileName2 = ConfigurationManager.AppSettings["pontajeFileName"];
            string fileName2 = "..\\..\\..\\data\\pontaje.txt";
            IValidator<Pontaj> vali = new PontajValidator();

            IRepository<string, Pontaj> repo1 = new PontajInFileRepository(vali, fileName2);
            PontajService service = new PontajService(repo1);
            return service;
        }

        private static void Sem1112()
        {
            List<Angajat> angajati = GetAngajatService().FindAllAngajati();
            // 1. afisati doar angajatii care au nivelul junior - extension methods "Where"

            List<Angajat> juniors = angajati
                .Where(angajat => angajat.Nivel == KnowledgeLevel.Junior)
                .ToList();
            juniors.ForEach(Console.WriteLine);

            Console.WriteLine("\n------");
            //2  - cerinta 1 din pdf sem11-12  extension methods 

            List<Angajat> ordonati = angajati.OrderBy(angajat => angajat.Nivel)
                .ThenByDescending(angajat => angajat.VenitPeOra)
                .ToList();

            ordonati.ForEach(Console.WriteLine);
            Console.WriteLine("\n------");

            //2  - cerinta 1 din pdf sem11-12  sql like 

            var ordonatisql = from angajat in angajati
                              orderby angajat.Nivel
                              orderby angajat.VenitPeOra descending
                              select angajat;
            ordonatisql.ToList().ForEach(Console.WriteLine);
            Console.WriteLine("\n------");

            //2 cate ore dureaza in medie fiecare tip de sarcina


            List<Sarcina> sarcini = GetSarcinaService().FindAllSarcini();

            Dictionary<Dificultate, double> ore = sarcini.GroupBy(sarcina => sarcina.TipDificultate)
                .Select(v => Tuple.Create(v.Key, v.Average(ora => ora.NrOreEstimate)))
                .ToDictionary(v => v.Item1, v => v.Item2);

            foreach (var ora in ore) Console.WriteLine(ora.Key + ":" + ora.Value);

            //3 primii doi cei mai harnici angajati

            Console.WriteLine("\n------");

            List<Pontaj> pontaje = GetPontajService().FindAllPontaje();

            List<Angajat> harnici = angajati
                .OrderByDescending(a => a.VenitPeOra *
                    pontaje.Where(p => p.Angajat.ID == a.ID)
                           .Select(p => p.Sarcina.NrOreEstimate).Sum())
                .Take(2)
                .ToList();

            var harnicisql = (from angajat in angajati
                              orderby angajat.VenitPeOra *
                                (from pontaj in pontaje
                                 where pontaj.Angajat.ID == angajat.ID
                                 select pontaj.Sarcina)
                                 .Sum(sarcina => sarcina.NrOreEstimate)
                              descending
                              select angajat)
                              .Take(2)
                              .ToList();

            harnici.ForEach(a =>
            {
                double venit = a.VenitPeOra *
                        pontaje.Where(p => p.Angajat.ID == a.ID)
                               .Select(p => p.Sarcina.NrOreEstimate).Sum();
                Console.WriteLine($"{a} : {venit}");
            });
            Console.WriteLine();
            harnicisql.ForEach(Console.WriteLine);
        }

    }
}
