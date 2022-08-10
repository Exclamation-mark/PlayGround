import {Component, Injector} from '@angular/core';
import { ThemeService } from './theme.service';
import {createCustomElement} from '@angular/elements';
import {MonacoInputComponent} from './pages/welcome/web-component/monaco-input/monaco-input.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less'],
})
export class AppComponent {
  isCollapsed = false;

  constructor(private themeService: ThemeService,
              injector: Injector) {
    // Convert `PopupComponent` to a custom element.
    const monin = createCustomElement(MonacoInputComponent, {injector});
    // Register the custom element with the browser.
    customElements.define('monaco-input-component', monin);
  }

  toggleTheme(): void {
    this.themeService.toggleTheme().then();
  }
}
