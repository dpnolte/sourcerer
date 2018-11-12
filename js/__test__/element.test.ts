import {
  DrawerLayout,
  DrawerlayoutTypes,
} from '../drawerlayout';
import { ViewBase } from '../element';
import { CoordinatorLayout, CoordinatorlayoutTypes } from '../coordinatorlayout';
import { AppBarLayout, NavigationView, ComponentsMaterialTypes } from '../componentsmaterial';
import { AppcompatToolbar } from '../appcompat';
import { NestedScrollView } from '../core';
import { LinearLayout, MainTypes } from '../main';

describe('testing element', () => {
  test('single option is set', () => {
      const layout = DrawerLayout({ colorPrimaryDark: 'white'}).build();
      const drawerLayout = layout.map.drawerLayout as ViewBase<DrawerlayoutTypes.DrawerLayoutAttributes, {}>;
      expect(drawerLayout.attributes.colorPrimaryDark).toEqual('white');
  });
  test('single child is set', () => {
    const layout = DrawerLayout(
      {},
      [CoordinatorLayout({ id: 'test' })]
    ).build();
    const drawerLayout = layout.map.drawerLayout as ViewBase<DrawerlayoutTypes.DrawerLayoutAttributes, {}>;
    expect(drawerLayout).toHaveProperty("children")
    const children = drawerLayout.children as String[]
    expect(children[0]).toEqual('drawerLayout_coordinatorLayout');
    const coordinatorLayout = layout.map.drawerLayout_coordinatorLayout as ViewBase<CoordinatorlayoutTypes.CoordinatorLayoutAttributes, {}>;
    expect(coordinatorLayout.attributes.id).toEqual('test');
  });
  test('single child and option is set', () => {
    const layout = DrawerLayout(
      { id: 'noot' },
      [CoordinatorLayout()],
    ).build();
    const drawerLayout = layout.map.drawerLayout as ViewBase<DrawerlayoutTypes.DrawerLayoutAttributes, {}>;
    expect(drawerLayout).toHaveProperty("children")
    const children = drawerLayout.children as String[]
    expect(children[0]).toEqual('drawerLayout_coordinatorLayout');
    expect(drawerLayout.attributes.id).toEqual('noot');
  });
  test('drawer with navigation layout', () => {
    const layout = DrawerLayout(
      { layout_width: "match_parent", layout_height: "match_parent"}, 
      [
        CoordinatorLayout(
          { background: "#FEFEFE" },
          [
            AppBarLayout(
              { layout_height: "290dp" },
              [          
                AppcompatToolbar({ title: 'Navigation Playground' }),
              ]
            ),
            NestedScrollView(
              {},
              [LinearLayout({ orientation: MainTypes.OrientationEnum.vertical })]
            ),
          ]
        ),
        NavigationView(
          { 
            layout_gravity: [ 
              DrawerlayoutTypes.LayoutGravityFlagsEnum.start,
              DrawerlayoutTypes.LayoutGravityFlagsEnum.left
            ]
          }
        ),
      ]).build();
    const appBarLayout = layout.map.drawerLayout_coordinatorLayout_appBarLayout as ViewBase<ComponentsMaterialTypes.AppBarLayoutAttributes, MainTypes.ViewGroupLayoutParamsAttributes>;
    expect(appBarLayout.attributes.layout_height).toEqual("290dp");
    expect(appBarLayout).toHaveProperty("children")

    const nestedScrollView = layout.map.drawerLayout_coordinatorLayout_nestedScrollView as ViewBase<ComponentsMaterialTypes.AppBarLayoutAttributes, MainTypes.ViewGroupLayoutParamsAttributes>;
    const children = nestedScrollView.children as String[]
    expect(children[0]).toEqual('drawerLayout_coordinatorLayout_nestedScrollView_linearLayout');

    const navigationView = layout.map.drawerLayout_navigationView as ViewBase<ComponentsMaterialTypes.NavigationViewAttributes, DrawerlayoutTypes.DrawerLayoutLayoutParamsAttributes>
    expect(navigationView.attributes.layout_gravity).toEqual(['start', 'left']);
  });
  test('converts to json array', () => {
    const layout = DrawerLayout(
      { layout_width: "match_parent", layout_height: "match_parent"}, 
      [
        CoordinatorLayout(
          { background: "#FEFEFE" },
          [
            AppBarLayout(
              { layout_height: "290dp" },
              [          
                AppcompatToolbar({ title: 'Navigation Playground' }),
              ]
            ),
            NestedScrollView(
              {},
              [LinearLayout({ orientation: MainTypes.OrientationEnum.vertical })]
            ),
          ]
        ),
        NavigationView(
          { 
            layout_gravity: [ 
              DrawerlayoutTypes.LayoutGravityFlagsEnum.start,
              DrawerlayoutTypes.LayoutGravityFlagsEnum.left
            ]
          }
        ),
      ]).build();
    //console.log(layout.toJson());
    expect(layout.toJson()).toEqual(jsonOutput);
  });
});

// tslint:disable-next-line:max-line-length
const jsonOutput = '[{"id":"drawerLayout","type":"drawerLayout","attributes":{"layout_width":"match_parent","layout_height":"match_parent"},"children":["drawerLayout_coordinatorLayout","drawerLayout_navigationView"]},{"id":"drawerLayout_coordinatorLayout","type":"coordinatorLayout","attributes":{"background":"#FEFEFE"},"children":["drawerLayout_coordinatorLayout_appBarLayout","drawerLayout_coordinatorLayout_nestedScrollView"]},{"id":"drawerLayout_coordinatorLayout_appBarLayout","type":"appBarLayout","attributes":{"layout_height":"290dp"},"children":["drawerLayout_coordinatorLayout_appBarLayout_appcompat.toolbar"]},{"id":"drawerLayout_coordinatorLayout_appBarLayout_appcompat.toolbar","type":"appcompat.toolbar","attributes":{"title":"Navigation Playground"},"children":[]},{"id":"drawerLayout_coordinatorLayout_nestedScrollView","type":"nestedScrollView","attributes":{},"children":["drawerLayout_coordinatorLayout_nestedScrollView_linearLayout"]},{"id":"drawerLayout_coordinatorLayout_nestedScrollView_linearLayout","type":"linearLayout","attributes":{"orientation":"vertical"},"children":[]},{"id":"drawerLayout_navigationView","type":"navigationView","attributes":{"layout_gravity":["start","left"]},"children":[]}]';
