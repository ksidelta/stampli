import { createBrowserHistory, History } from 'history';
import { Routes } from '../routes/Routes';

export class RoutingService {
  constructor(public history: History) {}

  goToBusinessOptionsPage() {
    this.history.push(Routes.business.root);
  }

  goToBusinessSettingsPage() {
    this.history.push(Routes.business.settings);
  }
}

export const RoutingServiceInstance = new RoutingService(createBrowserHistory());
