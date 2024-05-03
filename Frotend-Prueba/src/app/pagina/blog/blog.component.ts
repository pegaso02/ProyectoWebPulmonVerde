import { Component } from '@angular/core';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrl: './blog.component.css'
})
export class BlogComponent {
    title = 'Blog';

    itemsMenu = [{
      'titulo': 'QUIENES SOMOS',
      'url': '\src\assets\index.html'
    }]
}