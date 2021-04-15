import { createBrowserHistory, History } from 'history';

export class RoutingService {
  constructor(public history: History) {}

  goToBusinessOptionsPage() {
    this.history.push('/business/');
  }
}

export const RoutingServiceInstance = new RoutingService(createBrowserHistory());
