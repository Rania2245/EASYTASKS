import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'charge-journaliere',
        data: { pageTitle: 'easyTaskApp.chargeJournaliere.home.title' },
        loadChildren: () => import('./charge-journaliere/charge-journaliere.module').then(m => m.ChargeJournaliereModule),
      },
      {
        path: 'projet',
        data: { pageTitle: 'easyTaskApp.projet.home.title' },
        loadChildren: () => import('./projet/projet.module').then(m => m.ProjetModule),
      },
      {
        path: 'livrable',
        data: { pageTitle: 'easyTaskApp.livrable.home.title' },
        loadChildren: () => import('./livrable/livrable.module').then(m => m.LivrableModule),
      },
      {
        path: 'activite',
        data: { pageTitle: 'easyTaskApp.activite.home.title' },
        loadChildren: () => import('./activite/activite.module').then(m => m.ActiviteModule),
      },
      {
        path: 'maintenance',
        data: { pageTitle: 'easyTaskApp.maintenance.home.title' },
        loadChildren: () => import('./maintenance/maintenance.module').then(m => m.MaintenanceModule),
      },
      {
        path: 'ressource',
        data: { pageTitle: 'easyTaskApp.ressource.home.title' },
        loadChildren: () => import('./ressource/ressource.module').then(m => m.RessourceModule),
      },
      {
        path: 'client',
        data: { pageTitle: 'easyTaskApp.client.home.title' },
        loadChildren: () => import('./client/client.module').then(m => m.ClientModule),
      },
      {
        path: 'estimation',
        data: { pageTitle: 'easyTaskApp.estimation.home.title' },
        loadChildren: () => import('./estimation/estimation.module').then(m => m.EstimationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
