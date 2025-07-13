import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-root',
  template: `
    <h1>{{ title }}</h1>
    <router-outlet></router-outlet>`,
  imports: [RouterModule]
})
export class AppComponent {
  title = 'frontend-core';
}
