using BestStoreApi.Dao;
using BestStoreApi.Filters;
using BestStoreApi.Session;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using MongoDB.Driver;
using System;

namespace BestStoreApi
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            #region Singleton
            IMongoClient mc = new MongoClient(Configuration.GetConnectionString("mongoDb"));

            services.AddSingleton<IMongoClient>(mc);
            services.AddSingleton<ProdutoDAO>(new ProdutoDAO(mc));
            services.AddSingleton<UsuarioDAO>(new UsuarioDAO(mc));
            services.AddSingleton<LojaDAO>(new LojaDAO(mc));

            #endregion


            #region Redis config

            services.AddDistributedRedisCache(options =>
            {
                options.Configuration =
                    Configuration.GetConnectionString("Redis");

                options.InstanceName = "BestStore";
            });

            #endregion

            #region Cors

            services.AddCors(options =>
            {
                options.AddPolicy("AllowMyOrigin",
                    builder => builder
                        .WithOrigins("https://localhost:44373")
                        .AllowAnyHeader()
                        .AllowAnyMethod()
                        .AllowCredentials()
                        );

            });

            #endregion

            #region Cookies

            services.Configure<CookiePolicyOptions>(options =>
            {
                // This lambda determines whether user consent for non-essential cookies is needed for a given request.
                options.CheckConsentNeeded = context => false; // Default is true, make it false
                options.MinimumSameSitePolicy = SameSiteMode.None;
            });

            services.AddSession(s =>
            {
                s.Cookie.SecurePolicy = CookieSecurePolicy.Always;
                s.IdleTimeout = TimeSpan.FromMinutes(30);
                s.Cookie.Name = ".BestStore.Session";
                s.Cookie.HttpOnly = true;
                s.Cookie.IsEssential = true;
            });



            #endregion

            services.AddMvc(filter => filter.Filters.Add(new AutenticacaoFilterAsync()))
                .SetCompatibilityVersion(CompatibilityVersion.Version_2_2);

        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }
            app.UseCors("AllowMyOrigin");
            app.UseHttpsRedirection();
            app.UseSession();
            app.UseMvc();
        }
    }
}
