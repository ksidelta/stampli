import { createBrowserHistory, History } from 'history';

export class RoutingService {
  constructor(public history: History) {}

  goToBusinessLoginPage() {
    this.history.push('/business/login');
  }
}

export const RoutingServiceInstance = new RoutingService(createBrowserHistory());
