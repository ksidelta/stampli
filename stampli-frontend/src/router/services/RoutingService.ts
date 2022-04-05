import { createBrowserHistory, History } from 'history';
import { Routes } from '../routes/Routes';

export class RoutingService {
  constructor(public history: History) {
  }

  goToBusinessOptionsPage() {
    this.history.push(Routes.business.root);
  }

  goToBusinessSettingsPage() {
    this.history.push(Routes.business.settings);
  }

  goToBusinessQRCode() {
    this.history.push(Routes.business.qrCode);
  }

  goToHome() {
    this.history.push(Routes.home);
  }

  goToScanner() {
    this.history.push(Routes.scanner);
  }

  goToMenu(){
    this.history.push(Routes.menu)
  }

  goToStamps(){
    this.history.push(Routes.stamps)
  }
}

export const RoutingServiceInstance = new RoutingService(createBrowserHistory());
