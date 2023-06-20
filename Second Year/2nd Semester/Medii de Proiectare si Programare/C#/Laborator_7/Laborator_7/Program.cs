using System;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace Laborator_7
{
	class MainClass
	{
		static HttpClient client = new HttpClient(new LoggingHandler(new HttpClientHandler()));

		private static string URL_Base = "http://localhost:8080/triathlon/results";

		public static void Main(string[] args)
		{
			RunAsync().Wait();
		}


		static async Task RunAsync()
		{
			//Get one result
			Console.WriteLine("**********************************FindOne****************************************");
			int id = 1;
			Console.WriteLine("Get Result {0}", id);
			Result result1 = await GetResultAsync("http://localhost:8080/triathlon/results/"+id);
			Console.WriteLine("Am primit {0}", result1);
			
			
			//Create a result
			Console.WriteLine("*************************************Save****************************************");
			Result result = new Result
			{
				activity = "swimming",
				points = 10,
				participant = new Participant { first_name = "Andrada", id = 1, last_name = "Muresan", points = 20 },
				referee = new Referee { activity = "swimming", first_name = "Andrei", id = 1, last_name = "Pop", password = "abcd", username = "anonim123" }
			};
			
			Console.WriteLine("Create result {0}", result);
			Result res = await CreateResultAsync("http://localhost:8080/triathlon/results", result);
			Console.WriteLine("Am primit {0}", res);
			
			// // Get all results
			Console.WriteLine("*************************************GetAll****************************************");
			Result[] results = await GetAllResultsAsync("http://localhost:8080/triathlon/results");
			foreach (Result result2 in results)
			{
				if(result2.id > 100)
					Console.WriteLine(result2);
			}
			
			// // Delete a result
			Console.WriteLine("************************************Delete****************************************");
			int id2 = 117;
			Console.WriteLine("Delete Result {0}", id2);
			bool result3 = await DeleteResultAsync("http://localhost:8080/triathlon/results/"+id);
			Console.WriteLine("Am sters {0}", result1);
			
			//	Update a result
			Console.WriteLine("************************************Update****************************************");
			result1.points = 15;
			Console.WriteLine("Update result with id {0}", result1.id);
			bool result4 = await UpdateResultAsync("http://localhost:8080/triathlon/results/" + result1.id, result1);

		}

		static async Task<bool> DeleteResultAsync(string path)
		{
			HttpWebResponse product = null;
			HttpResponseMessage response = await client.DeleteAsync(path);
			if (response.IsSuccessStatusCode)
			{
				product = await response.Content.ReadAsAsync<HttpWebResponse>();
			}
			return product != null;
		}
		
		
		static async Task<Result> GetResultAsync(string path)
		{
			Result product = null;
			HttpResponseMessage response = await client.GetAsync(path);
			if (response.IsSuccessStatusCode)
			{
				product = await response.Content.ReadAsAsync<Result>();
			}
			return product;
		}

		static async Task<Result[]> GetAllResultsAsync(string path)
		{
			Result[] product = null;
			HttpResponseMessage response = await client.GetAsync(path);
			if (response.IsSuccessStatusCode)
			{
				product = await response.Content.ReadAsAsync<Result[]>();
			}

			return product;
		}

		
		static async Task<Result> CreateResultAsync(string path, Result result)
		{
			Result theResult = null;
			Console.WriteLine("JSON GENERAT     " + JsonConvert.SerializeObject(result));
			HttpResponseMessage response = await client.PostAsJsonAsync(path, result);
			if (response.IsSuccessStatusCode)
			{
				theResult = await response.Content.ReadAsAsync<Result>();
			}
			return theResult;
		}

		static async Task<bool> UpdateResultAsync(string path, Result result)
		{
			HttpWebResponse product = null;
			HttpResponseMessage response = await client.PutAsJsonAsync(path,result);
			if (response.IsSuccessStatusCode)
			{
				product = await response.Content.ReadAsAsync<HttpWebResponse>();
			}
			return product != null;
		}
	}
	
	public class Referee
	{
		//[JsonProperty("id")]
		public int id { get; set; }
		
		//[JsonProperty("username")]
		public string username { get; set; }
		
		//[JsonProperty("password")]
		public string password { get; set; }
		
		//[JsonProperty("first_name")]
		public string first_name { get; set; }
		
		//[JsonProperty("last_name")]
		public string last_name { get; set; }
		
		//[JsonProperty("activity")]
		public string activity { get; set; }

		public override string ToString()
		{
			return string.Format("[Referee: Username={0}, Password={1}, Id={2}, FirstName={3}, LatName={4}, Activity={5}]", username, password, id, first_name, last_name, activity);
		}
	}
	
	public class Participant
	{
		//[JsonProperty("id")]
		public int id { get; set; }

		//[JsonProperty("first_name")]
		public string first_name { get; set; }
		
		//[JsonProperty("last_name")]
		public string last_name { get; set; }
		
		//[JsonProperty("points")]
		public int points { get; set; }

		public override string ToString()
		{
			return string.Format("[Participant: Id={0}, FirstName={1}, LatName={2}, Points={3}]", id, first_name, last_name, points);
		}
	}
	
	public class Result
	{
		//[JsonProperty("id")]
		public int id { get; set; }

		//[JsonProperty("participant")]
		public Participant participant { get; set; }
		
		//[JsonProperty("referee")]
		public Referee referee { get; set; }
		
		//[JsonProperty("activity")]
		public string activity { get; set; }
		
		//[JsonProperty("points")]
		public int points { get; set; }

		public override string ToString()
		{
			return string.Format("[Result: Id={0}, Participant={1}, Referee={2}, Activity={3}, Points={4}]", id, participant, referee, activity, points);
		}
	}
	
	public class LoggingHandler : DelegatingHandler
    {
        public LoggingHandler(HttpMessageHandler innerHandler)
            : base(innerHandler)
        {
        }
    
        protected override async Task<HttpResponseMessage> SendAsync(HttpRequestMessage request, CancellationToken cancellationToken)
        {
            Console.WriteLine("Request:");
            Console.WriteLine(request.ToString());
            if (request.Content != null)
            {
                Console.WriteLine(await request.Content.ReadAsStringAsync());
            }
            Console.WriteLine();
    
            HttpResponseMessage response = await base.SendAsync(request, cancellationToken);
    
            Console.WriteLine("Response:");
            Console.WriteLine(response.ToString());
            if (response.Content != null)
            {
                Console.WriteLine(await response.Content.ReadAsStringAsync());
            }
            Console.WriteLine();
    
            return response;
        }
    }
	
}
